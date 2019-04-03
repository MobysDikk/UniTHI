// A simple variant of the game Snake
//
// Used for teaching in classes
//
// Author:
// Franz Regensburger
// Ingolstadt University of Applied Sciences
// (C) 2011
//



#include <curses.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>//Durch das Inkludieren der Datei stdbool.h sind der Datentyp bool und seine Elemente true und flase des neuen Typs bool.
#include <time.h>
#include <string.h>
#include <unistd.h>
#include "prep.h"
#include "messages.h"
#include "worm.h"
#include "worm_model.h"
#include "board_model.h"

// Management of the game
void initializeColors();
void readUserInput(struct worm* aworm,enum GameStates* agame_state ); //verarbtein die eingabe
enum ResCodes doLevel();// funktion die die endlos schleife main loop beinhaltet


// ********************************************************************************************
// Functions
// ********************************************************************************************


// ************************************
// Management of the game
// ************************************

// Initialize colors of the game
void initializeColors() {
  // Define colors of the game
  start_color();
  init_pair(COLP_USER_WORM, COLOR_GREEN, COLOR_BLACK);
  init_pair(COLP_FREE_CELL, COLOR_BLACK, COLOR_BLACK);
  init_pair(COLP_BARRIER,   COLOR_RED,   COLOR_BLACK);
}            //VorderGrund Farbe soll Grün sein zweiter parameter ist hintergrundfarbe

void readUserInput(struct worm* aworm,enum GameStates* agame_state ) {
  int ch; // For storing the key codes

  if ((ch = getch()) > 0) { //wird null wenn man nichts eingeben hat und blocking mode wenn eineeingabe statt findet niemals 0
    // Is there some user input?
    // Blocking or non-blocking depends of config of getch
    switch(ch) {
      case 'q' :    // User wants to end the show überprüfe ob eine taste getippt wird tastendruck shift und alt für diese tasten werden über die konstanten abgefragt
        *agame_state = WORM_GAME_QUIT;
        break;
      case KEY_UP :// User wants up
        setWormHeading(aworm, WORM_UP);
        break;
      case KEY_DOWN :// User wants down
        /* @012*/ setWormHeading(aworm, WORM_DOWN);
        break;
      case KEY_LEFT :// User wants left
        /* @012*/ setWormHeading(aworm, WORM_LEFT);
        break;
      case KEY_RIGHT :// User wants right
        /* @012*/ setWormHeading(aworm, WORM_RIGHT);
        break;
      case 's' : // User wants single step mode locking wartet bis zum nächsten tastendruck mit leertaste deaktiviert
        nodelay(stdscr, FALSE );
        // We simply make getch blocking
        break;
      case ' ' : // Terminate single step; make getch non-blocking again
        nodelay(stdscr, TRUE );  // Make getch non-blocking again
        break;
    }
  }
  return;
}

enum ResCodes doLevel() {

  struct worm userworm;// Local variable for storing the user's worm

  enum GameStates game_state; // The current game_state inwelchem status befinden wir uns

  enum ResCodes res_code; // Result code from functions
  bool end_level_loop;    // Indicates whether we should leave the main loop ob lvl schleife beendet werden soll

  struct pos bottomLeft;   // Start positions of the worm 

  // At the beginnung of the level, we still have a chance to win wir befinden uns im spiel
  game_state = WORM_GAME_ONGOING;

  // There is always an initialized user worm.
  // Initialize the userworm with its size, position, heading.
  bottomLeft.y =  getLastRow();
  bottomLeft.x =  0;

  res_code = initializeWorm( &userworm ,WORM_LENGTH, bottomLeft , WORM_RIGHT, COLP_USER_WORM);
  if ( res_code != RES_OK) {
    return res_code;//color 99 oder -1
  }


  //Show boarder line in order to separate the message area 
  showBoarderLine();
  // Show worm at its initial position zeichnen den wurm
  showWorm(&userworm);

  // Display all what we have set up until now extra refresh ist nötig da aussgabe eine knappe resource ist der puffer wird auf die tatsächlich ausgabe übergeben
  refresh();

  // Start the loop for this level HAUPTSCHLEIFE
  end_level_loop = false; // Flag for controlling the main loop
  while(!end_level_loop) { //Ist die negation von end level loop 
    // Process optional user input VERARBEITET DIE EINGABEN SELBST
    readUserInput(&userworm, &game_state); //in die variable game state zu schreiben adresse
    if ( game_state == WORM_GAME_QUIT ) {//wollen level schleife beenden
      end_level_loop = true;
      continue; // Go to beginning of the loop's block and check loop condition ein break würde an dieser stelle auch funktionieren
    }

    // Process userworm
    cleanWormTail(&userworm);
    // Now move the worm for one step
    moveWorm(&userworm, &game_state);
    // Bail out of the loop if something bad happened
    if ( game_state != WORM_GAME_ONGOING ) {
      end_level_loop =/* @016*/ true;
      continue; // Go to beginning of the loop's block and check loop condition
    }
    // Show the worm at its new position
    showWorm(&userworm);
    // END process userworm
    // Inform user about position and length of userworm in status window
    showStatus(&userworm);
    // Sleep a bit before we show the updated window
    napms(NAP_TIME);

    // Display all the updates
    refresh();

    // Start next iteration
  }

  // Preset res_code for rest of the function
  res_code = RES_OK;

  // For some reason we left the control loop of the current level.
  //Check why according to game_state
  switch (game_state) {
    case WORM_GAME_QUIT:
      //User must have typed 'q' for quit
      showDialog("Sie haben die aktuelle Runde abgebrochen!",
          "Bitte Taste druecken");
      break;
    case WORM_OUT_OF_BOUNDS:
      showDialog("Sie haben das Spiel verloren,"
          "weil Sie das Spielfeld verlassen haben",
          "Bitte Taste druecken");
      break;
    case WORM_CROSSING:
      showDialog("Sie haben das Spiel verloren,"
          "weil Sie einen Wurm gekreuzt haben",
          "Bitte Taste druecken");
      break;
    default:
      showDialog("Interner Fehler!","Bitte Taste druecken");
      //Set error result code. This should never happen.
      res_code = RES_INTERNAL_ERROR;
  }

  // However, in this version we do not yet check for the reason.
  // There is no user feedback at the moment!

  // Normal exit point
  return  res_code;
} // END of function doLevel

// ********************************************************************************************
// MAIN
// ********************************************************************************************

int main(void) {//muss int sein da wir ansonsten eine Warnung des C-Compilers bekommen
  enum ResCodes res_code;         // Result code from functions

  // Here we start
  initializeCursesApplication();  // Init various settings of our application
  initializeColors();             // Init colors used in the game

  // Maximal LINES and COLS are set by curses for the current window size.
  // Note: we do not cope with resizing in this simple examples!

  // Check if the window is large enough to display messages in the message area
  // a has space for at least one line for the worm
  if ( LINES < ROWS_RESERVED + MIN_NUMBER_OF_ROWS || COLS < MIN_NUMBER_OF_COLS ) {
    // Since we not even have the space for displaying messages
    // we print a conventional error message via printf after
    // the call of cleanupCursesApp()
    cleanupCursesApp();
    printf("Das Fenster ist zu klein: wir brauchen mindestens %dx%d\n",
        MIN_NUMBER_OF_COLS, MIN_NUMBER_OF_ROWS + ROWS_RESERVED );
    res_code = RES_FAILED; //wenn fenster zu klein
  } else {
    res_code =  doLevel();//deligiere die verantwortlichkeit des levels
    cleanupCursesApp();
  }

  return res_code;
}
