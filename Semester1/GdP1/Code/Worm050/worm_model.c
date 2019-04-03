// A simple variant of the game Snake
//
// Used for teaching in classes
//
// Author:
// Franz Regensburger
// Ingolstadt University of Applied Sciences
// (C) 2011
//
// The worm model

#include <curses.h>
#include "worm.h"
#include "board_model.h"
#include "worm_model.h"


// *****************************************************
// Functions concerning the management of the worm data
// *****************************************************

// START WORM_DETAIL
// The following functions all depend on the model of the worm

//Getters
struct pos getWormHeadPos(struct worm* aworm) {
      //Structures are passed by value!
      //-> we return a copy here
      return aworm->wormpos[aworm->headindex];
}

// Initialize the worm
extern enum ResCodes initializeWorm(struct worm* aworm ,int len_max,struct pos headpos, enum WormHeading dir, enum ColorPairs color) {
  int i; //Interratior variable ist aussagend in der literatur

  //Initialize last usable index to len_may -1
  aworm->maxindex = len_max -1;
  // Initalize headindex
  aworm->headindex = 0;//Index pointing to head position is set to 0
//Mark all elements as unused in the array of positions.
//This allows for the effext that the worm appears element by element at 
//the start of each level

  for (i = 0; i <= aworm->maxindex; i++) {
    aworm->wormpos[i].y = UNUSED_POS_ELEM;
    aworm->wormpos[i].x = UNUSED_POS_ELEM;
  }

  // Initialize position of worms head
  aworm->wormpos[aworm->headindex] = headpos;
  // Initialize the heading of the worm
  setWormHeading (aworm, dir);
  // Initialze color of the worm
  aworm->wcolor = color;

  return RES_OK;
}

extern void showWorm(struct worm* aworm) {
  // Due to our encoding we just need to show the head element
  // All other elements are already displayed
  placeItem(
      aworm->wormpos[aworm->headindex] ,
      //aworm->wormpos_x[aworm->headindex],
      SYMBOL_WORM_INNER_ELEMENT,aworm->wcolor);//dieser aufruf können wir ersetzten dirch showworm kapseln viel code in wenig code
}

extern void cleanWormTail(struct worm* aworm){
  int tailindex = (aworm->headindex + 1) % (aworm->maxindex +1);

  if (aworm->wormpos[tailindex].y != UNUSED_POS_ELEM) {
    placeItem(aworm->wormpos[tailindex],
        SYMBOL_FREE_CELL,
        COLP_FREE_CELL
        );
  }
}

extern void moveWorm(struct worm* aworm, enum GameStates* agame_state) {
  // Compute and store new head position according to current heading.
  
  struct pos headpos = aworm->wormpos[aworm->headindex];

    headpos.y += aworm->dy;
    headpos.x += aworm->dx;



  // Check if we would leave the display if we move the worm's head according
  // to worm's last direction.
  // We are not allowed to leave the display's window.
  if (headpos.x < 0) {
    *agame_state = WORM_OUT_OF_BOUNDS;
  } else if (headpos.x > getLastCol() ) {
    *agame_state =/* @011*/ WORM_OUT_OF_BOUNDS; //SIND WIR NOCH IM SPIELFELD ÜBERPRÜFUNG
  } else if (headpos.y < 0) {
    /* @011*/ *agame_state = WORM_OUT_OF_BOUNDS;
  } else if (headpos.y > getLastRow() ) {
    /* @011*/ *agame_state = WORM_OUT_OF_BOUNDS;
  } else {
    // We will stay within bounds.
    // So all is well
    // Do nothing
    if (isInUseByWorm(aworm, headpos)){
      *agame_state = WORM_CROSSING;
    }
  }


  if (*agame_state == WORM_GAME_ONGOING) {
    aworm->headindex = (aworm->headindex + 1) % (aworm->maxindex + 1);
    aworm->wormpos [aworm->headindex] = headpos;
   // aworm->wormpos_x [aworm->headindex] = headpos_x;
  }
}

extern bool isInUseByWorm(struct worm* aworm, struct pos new_headpos){
  bool collision = false;

  int i = aworm->headindex;

  do {
    if ( aworm->wormpos[i].y == new_headpos.y &&
         aworm->wormpos[i].x == new_headpos.x) {
      collision = true;
      break;
    }

    i = (i + aworm->maxindex) % (aworm->maxindex +1);
  } while (i != aworm->headindex &&
      aworm->wormpos[i].y != UNUSED_POS_ELEM);

  return collision;
}

// Setters
extern void setWormHeading(struct worm* aworm,enum WormHeading dir) {
  switch(dir) {
    case WORM_UP :// User wants up
      aworm->dx=0;
      aworm->dy=-1;
      break;
    case WORM_DOWN :// User wants down
      aworm->dx=0;
      aworm->dy=1;
      break;
    case WORM_LEFT :// User wants left
      aworm->dx=-1;
      aworm->dy=0; 
      break;
    case WORM_RIGHT :// User wants right
      aworm->dx=1;
      aworm->dy=0;
      break;
  }
}
