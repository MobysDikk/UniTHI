// A simple variant of the game Snake
//
// Used for teaching in classes
//
// Author:
// Franz Regensburger
// Ingolstadt University of Applied Sciences
// (C) 2011
//

#ifndef _WORM_H
#define _WORM_H
//Result codes of functions (Enumeration)
enum ResCodes {
     RES_OK,
     RES_FAILED,
 };

// Dimensions and bounds
#define NAP_TIME    100             // Time in milliseconds to sleep between updates of display (wie lange ein script laufen darf) 
#define MIN_NUMBER_OF_ROWS  3       // The guaranteed number of rows available for the board 
#define MIN_NUMBER_OF_COLS 10       // The guaranteed number of columns available for the board
#define WORM_LENGTH 20


// Codes for the array of positions
#define UNUSED_POS_ELEM -1


//Numbers for color pairs used by curses macro COLOR_PAIR (Enumeration)
enum ColorPairs {
      COLP_USER_WORM = 1,
       COLP_FREE_CELL,
};
    
// Symbols to display wurm besteht aus den nullen wird definiert 
#define SYMBOL_FREE_CELL ' '
#define SYMBOL_WORM_INNER_ELEMENT '0'


//Game state codes (Enumeration)
enum GameStates {
     WORM_GAME_ONGOING,
     WORM_OUT_OF_BOUNDS, // LEft Screen
     WORM_CROSSING,      //Worm head crossed another worm element
     WORM_GAME_QUIT,     // User likes to quit
};
        

#endif  // #define _WORM_H
