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

// ********************************************************************************************
// Constants, data structures
// ********************************************************************************************

//#define TRUE 1  //Richtig 1 falsch 0 künstlich erzeugt alles was nicht 0 ist ist ture
//#define FALSE 0 

// Result codes of functions
//#define RES_OK 0 
//#define RES_FAILED 1

// Dimensions and bounds
#define NAP_TIME    100             // Time in milliseconds to sleep between updates of display (wie lange ein script laufen darf) 
#define MIN_NUMBER_OF_ROWS  3       // The guaranteed number of rows available for the board 
#define MIN_NUMBER_OF_COLS 10       // The guaranteed number of columns available for the board
#define WORM_LENGTH 20
#define UNUSED_POS_ELEM -1



// Numbers for color pairs used by curses macro COLOR_PAIR wie gross muss das spielfeld mind sein 
//#define COLP_USER_WORM 1 

// Symbols to display wurm besteht aus den nullen wird definiert 
#define SYMBOL_FREE_CELL ' '
#define SYMBOL_WORM_INNER_ELEMENT '0'

// Game state codes (wird oft verwechselt mit den result codes) innerhalb meiner schleife beeinflussen wie die schleife weiter laufen soll nur ingame gegner bewegen bspw.
//#define  WORM_GAME_ONGOING  0
//#define  WORM_OUT_OF_BOUNDS 1   // Left screen
//#define  WORM_GAME_QUIT     2   // User likes to quit

// Directions for the worm (Definition der RIchtung 4 variablen richtungen werden definiert durch delta x und y)
//#define WORM_UP      0
//#define WORM_DOWN    1
//#define WORM_LEFT    2
//#define WORM_RIGHT   3

// ********************************************************************************************
                                      // Global variables
// ********************************************************************************************

//Result codes of functions (Enumeration)
enum ResCodes {
  RES_OK,
  RES_FAILED,
};

//Numbers for color pairs used by curses macro COLOR_PAIR (Enumeration)
enum ColorPairs {
  COLP_USER_WORM = 1,
  COLP_FREE_CELL,
};

//Game state codes (Enumeration)
enum GameStates {
  WORM_GAME_ONGOING,
  WORM_OUT_OF_BOUNDS, // LEft Screen
  WORM_CROSSING,      //Worm head crossed another worm element
  WORM_GAME_QUIT,     // User likes to quit
};

//Directions for the worm
enum WormHeading {
  WORM_UP,
  WORM_DOWN,
  WORM_LEFT,
  WORM_RIGHT,
};

// Data defining the worm
int theworm_wormpos_y[WORM_LENGTH];  // y-coordinate of the worm's head 
int theworm_wormpos_x[WORM_LENGTH];  // x-coordinate of the worm's head

int theworm_maxindex;
int theworm_headindex;

// The current heading of the worm (Jeder wurm braucht seine eigene werte globale variablen da wir nur einen wurm haben)
// These are offsets from the set {-1,0,+1}
int theworm_dx;
int theworm_dy;
enum ColorPairs theworm_wcolor; 


// ********************************************************************************************
// Forward declarations of functions
// ********************************************************************************************

// This avoids problems with the sequence of function declarations inside the code.
// Note: this kind of problem is solved by header files later on!

// Management of the game
void initializeColors();
void readUserInput(enum GameStates* agame_state ); //verarbtein die eingabe
enum ResCodes doLevel();// funktion die die endlos schleife main loop beinhaltet

// Standard curses initialization and cleanup
void initializeCursesApplication();
void cleanupCursesApp(void);

// Placing and removing items from the game board
// Check boundaries of game board
void placeItem(int y, int x, chtype symbol, enum ColorPairs color_pair); //bestimmtes zeichen an einer bestimmten position auszugeben
int getLastRow();
int getLastCol();

// Functions concerning the management of the worm data
enum ResCodes initializeWorm(int len_max, int headpos_y, int headpos_x,enum WormHeading dir, enum ColorPairs color); //start werte des Wurms

void showWorm();
void cleanWormTail();

void moveWorm(enum GameStates* agame_state);     //bewegt den wurm um einen schritt
bool isInUseByWorm(int new_headpos_y, int new_headpos_x); // JA ODER NEIN
void setWormHeading(enum WormHeading dir)       ;// richtung 0123 in xy delta

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
       init_pair(COLP_USER_WORM, /* @002,*/COLOR_GREEN, COLOR_BLACK);
       init_pair(COLP_FREE_CELL, COLOR_BLACK, COLOR_BLACK);
}            //VorderGrund Farbe soll Grün sein zweiter parameter ist hintergrundfarbe

void readUserInput(enum GameStates* agame_state ) {
  int ch; // For storing the key codes

  if ((ch = getch()) > 0) { //wird null wenn man nichts eingeben hat und blocking mode wenn eineeingabe statt findet niemals 0
    // Is there some user input?
    // Blocking or non-blocking depends of config of getch
    switch(ch) {
      case 'q' :    // User wants to end the show überprüfe ob eine taste getippt wird tastendruck shift und alt für diese tasten werden über die konstanten abgefragt
        *agame_state = WORM_GAME_QUIT;
        break;
      case KEY_UP :// User wants up
        setWormHeading(WORM_UP);
        break;
      case KEY_DOWN :// User wants down
        /* @012*/ setWormHeading(WORM_DOWN);
        break;
      case KEY_LEFT :// User wants left
        /* @012*/ setWormHeading(WORM_LEFT);
        break;
      case KEY_RIGHT :// User wants right
        /* @012*/ setWormHeading(WORM_RIGHT);
        break;
      case 's' : // User wants single step mode locking wartet bis zum nächsten tastendruck mit leertaste deaktiviert
        /*@013*/nodelay(stdscr, FALSE );
        // We simply make getch blocking
        break;
      case ' ' : // Terminate single step; make getch non-blocking again
        /* @013*/nodelay(stdscr, TRUE );  // Make getch non-blocking again
        break;
    }
  }
  return;
}

enum ResCodes doLevel() {
  enum GameStates game_state; // The current game_state inwelchem status befinden wir uns

  int res_code; // Result code from functions
  bool end_level_loop;    // Indicates whether we should leave the main loop ob lvl schleife beendet werden soll

  int bottomLeft_y, bottomLeft_x;   // Start positions of the worm 

  // At the beginnung of the level, we still have a chance to win wir befinden uns im spiel
  game_state = WORM_GAME_ONGOING;

  // There is always an initialized user worm.
  // Initialize the userworm with its size, position, heading.
  bottomLeft_y =  getLastRow();
  bottomLeft_x =  0;

  res_code = initializeWorm(WORM_LENGTH, bottomLeft_y, bottomLeft_x , WORM_RIGHT, COLP_USER_WORM);
  if ( res_code != RES_OK) {
    return res_code;//color 99 oder -1
  }

  // Show worm at its initial position zeichnen den wurm
  showWorm();

  // Display all what we have set up until now extra refresh ist nötig da aussgabe eine knappe resource ist der puffer wird auf die tatsächlich ausgabe übergeben
  refresh();

  // Start the loop for this level HAUPTSCHLEIFE
  end_level_loop = false; // Flag for controlling the main loop
  while(!end_level_loop) { //Ist die negation von end level loop 
    // Process optional user input VERARBEITET DIE EINGABEN SELBST
    readUserInput( &game_state); //in die variable game state zu schreiben adresse
    if ( game_state == WORM_GAME_QUIT ) {//wollen level schleife beenden
      end_level_loop =/* @014*/ true;
      continue; // Go to beginning of the loop's block and check loop condition ein break würde an dieser stelle auch funktionieren
    }

    // Process userworm
    cleanWormTail();
    // Now move the worm for one step
    moveWorm(/*@015*/&game_state);
    // Bail out of the loop if something bad happened
    if ( game_state != WORM_GAME_ONGOING ) {
      end_level_loop =/* @016*/ true;
      continue; // Go to beginning of the loop's block and check loop condition
    }
    // Show the worm at its new position
    showWorm();
    // END process userworm

    // Sleep a bit before we show the updated window
    napms(NAP_TIME);

    // Display all the updates
    refresh();

    // Start next iteration
  }

  // Preset res_code for rest of the function
  res_code = RES_OK;

  // For some reason we left the control loop of the current level.
  // However, in this version we do not yet check for the reason.
  // There is no user feedback at the moment!

  // Normal exit point
  return /*@017*/ res_code;
}

// *********************************************
// Standard curses initialization and cleanup
// *********************************************

// Initialize application with respect to curses settings
void initializeCursesApplication() {
  initscr(); // Initialize the curses screen

  // Note:
  // The call to initscr() defines various global variables of the curses framework.
  // stdscr, LINES, COLS, TRUE, FALSE

  noecho();  // Characters typed ar not echoed
  cbreak();  // No buffering of stdin
  nonl();    // Do not translate 'return key' on keyboard to newline character
  keypad(stdscr, TRUE); // Enable the keypad
  curs_set(0);          // Make cursor invisible
  // Begin in non-single-step mode (getch will not block)
  nodelay(stdscr, TRUE);  // make getch to be a non-blocking call
}

// Reset display to normale state and terminate curses application
void cleanupCursesApp(void)
{
  standend();   // Turn off all attributes
  refresh();    // Write changes to terminal
  curs_set(1);  // Set cursor state to normal visibility
  endwin();     // Terminate curses application
}

// *************************************************
// Placing and removing items from the game board
// Check boundaries of game board
// *************************************************

// Place an item onto the curses display.
void placeItem(int y, int x, chtype symbol, enum ColorPairs color_pair) {

  //  Store item on the display (symbol code)
  move(y, x);                         // Move cursor to (y,x)
  attron(COLOR_PAIR(color_pair));     // Start writing in selected color
  addch(/*@006*/symbol);                      // Store symbol on the virtual display 
  attroff(COLOR_PAIR(color_pair));    // Stop writing in selected color
}

// Getters

// Get the last usable row on the display
int getLastRow() {
  return/* @003*/LINES -1 ;
}  // Springt in die letzte Zeile des Termianls

// Get the last usable column on the display
int getLastCol() {
  /* @004*/return COLS -1;
}  //springt in die letzte Spalte des terminals

// *****************************************************
// Functions concerning the management of the worm data
// *****************************************************

// START WORM_DETAIL
// The following functions all depend on the model of the worm

// Initialize the worm
enum ResCodes initializeWorm(int len_max,int headpos_y, int headpos_x, enum WormHeading dir, enum ColorPairs color) {
  int i; //Interratior variable ist aussagend in der literatur

  theworm_maxindex = len_max -1;
  theworm_headindex = 0;

  for (i = 0; i <= theworm_maxindex; i++) {
  theworm_wormpos_y[i] = UNUSED_POS_ELEM;
  theworm_wormpos_x [i] = UNUSED_POS_ELEM;
  }
  
  
  // Initialize position of worms head
  theworm_wormpos_y [theworm_headindex] = headpos_y;
  theworm_wormpos_x [theworm_headindex] = headpos_x;//Globale variablen zentral zu bestücken

  // Initialize the heading of the worm
     setWormHeading (dir);

  // Initialze color of the worm
  theworm_wcolor = color;

  return RES_OK;
}

// Show the worms's elements on the display
// Simple version
void showWorm() {
  placeItem(theworm_wormpos_y[theworm_headindex],
            theworm_wormpos_x[theworm_headindex],
      SYMBOL_WORM_INNER_ELEMENT,theworm_wcolor);
}



void cleanWormTail(){
  int tailindex = (theworm_headindex+1) % (theworm_maxindex+1);
  
  if (theworm_wormpos_y[tailindex] != UNUSED_POS_ELEM) {
    placeItem(
        theworm_wormpos_y[tailindex],
        theworm_wormpos_x[tailindex],
        SYMBOL_FREE_CELL,
        COLP_FREE_CELL
        );
    }
  }





void moveWorm(enum GameStates* agame_state) {
  // Compute and store new head position according to current heading.
         int headpos_y = theworm_wormpos_y[theworm_headindex] + theworm_dy;
         int headpos_x =  theworm_wormpos_x[theworm_headindex]  + theworm_dx;//bewegt wurm einfach um die delta werte

  // Check if we would leave the display if we move the worm's head according
  // to worm's last direction.
  // We are not allowed to leave the display's window.
  if (headpos_x < 0) {
    *agame_state = WORM_OUT_OF_BOUNDS;
  } else if (headpos_x > getLastCol() ) { 
    *agame_state = WORM_OUT_OF_BOUNDS; //SIND WIR NOCH IM SPIELFELD ÜBERPRÜFUNG
  } else if (headpos_y < 0) {  
    /* @011*/ *agame_state = WORM_OUT_OF_BOUNDS; 
  } else if (headpos_y > getLastRow() ) {
    /* @011*/ *agame_state = WORM_OUT_OF_BOUNDS; 
  } else {
    // We will stay within bounds.
    // So all is well
    // Do nothing
    if (isInUseByWorm(headpos_y, headpos_x)){
      *agame_state = WORM_CROSSING;
    }
  }
  
  if (*agame_state == WORM_GAME_ONGOING) {
      theworm_headindex = (theworm_headindex + 1) % (theworm_maxindex + 1);
      theworm_wormpos_y [theworm_headindex] = headpos_y;
      theworm_wormpos_x [theworm_headindex] = headpos_x;
  }
}


bool isInUseByWorm(int new_headpos_y, int new_headpos_x){
    bool collision = false;
    
    int i = theworm_headindex;



    do {
      if ( theworm_wormpos_y[i] == new_headpos_y &&
          theworm_wormpos_x[i] == new_headpos_x) {
            collision = true;
            break;
      }

      i = (i + theworm_maxindex) % (theworm_maxindex +1);
    } while (i != theworm_headindex &&
           theworm_wormpos_y[i] != UNUSED_POS_ELEM);

    return collision;
}



// Setters
void setWormHeading(enum WormHeading dir) {
  switch(dir) {
    case WORM_UP :// User wants up
      theworm_dx=0;
      theworm_dy=-1;
      break;
    case WORM_DOWN :// User wants down
      /* @005*/theworm_dx=0;
      /* @005*/theworm_dy=1;
      break;
    case/* @005*/ WORM_LEFT :// User wants left
      /* @005*/theworm_dx=-1;
      /* @005*/theworm_dy=0; 
      break;
    case/* @005*/ WORM_RIGHT :// User wants right
      /* @005*/theworm_dx=1;
      /* @005*/theworm_dy=0;
      break;
  }
} 

// END WORM_DETAIL
// ********************************************************************************************

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
  if ( LINES < MIN_NUMBER_OF_ROWS || COLS < MIN_NUMBER_OF_COLS ) {
    // Since we not even have the space for displaying messages
    // we print a conventional error message via printf after
    // the call of cleanupCursesApp()
    cleanupCursesApp();
    printf("Das Fenster ist zu klein: wir brauchen mindestens %dx%d\n",
        MIN_NUMBER_OF_COLS, MIN_NUMBER_OF_ROWS );
    res_code = RES_FAILED; //wenn fenster zu klein
  } else {
    res_code =  doLevel();//deligiere die verantwortlichkeit des levels
    cleanupCursesApp();
  }

  return /* @001;*/ res_code;
}
