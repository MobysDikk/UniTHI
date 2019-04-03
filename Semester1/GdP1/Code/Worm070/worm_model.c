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

//START WORM_DETAIL
////THE follwing functions all depend on the model of the worm
// Initialize the worm
enum ResCodes initializeWorm(struct worm* aworm, int len_max, int len_cur, struct pos headpos, enum WormHeading dir, enum ColorPairs color) {

  // Initialize last usable index to len_max -1: theworm_maxindex
  aworm->maxindex = len_max -1;
  aworm->cur_lastindex = len_cur -1;//momentane last index

  // Initialize headindex: theworm_headindex
  aworm->headindex = 0;

  // Set all elements to unused 
  int i;//Interratiior variable ist aussagend in der literatur
  for(i=0;i<=aworm->maxindex;i++){
    aworm->wormpos[i].x = UNUSED_POS_ELEM;
    aworm->wormpos[i].y = UNUSED_POS_ELEM;
  }

  // Initialize position of worms head
  aworm->wormpos[aworm->headindex] = headpos;

  // Initialize the heading of the worm
  setWormHeading(aworm, dir);

  // Initialze color of the worm
  aworm->wcolor = color;

  return RES_OK;
}

// Show the worms's elements on the display
// Simple version
void showWorm(struct board* aboard, struct worm* aworm) {
  // Due to our encoding we just need to show the head element
  // All other elements are already displayed

  int i = aworm->headindex;
  int tailindex = (aworm->headindex+1)%(aworm->cur_lastindex+1);
  do { 
    if(aworm->wormpos[i].x == aworm->wormpos[aworm->headindex].x &&
        aworm->wormpos[i].y == aworm->wormpos[aworm->headindex].y){
 // Head
  placeItem(aboard,
      BC_USED_BY_WORM, 
      aworm->wormpos[aworm->headindex],
      SYMBOL_WORM_HEAD_ELEMENT,
      aworm->wcolor);}


    else if  (aworm->wormpos[i].x == aworm->wormpos[tailindex].x &&
              aworm->wormpos[i].y == aworm->wormpos[tailindex].y ) {

 //Tail
      placeItem(aboard,
                BC_USED_BY_WORM, 
                aworm->wormpos[tailindex], 
                SYMBOL_WORM_TAIL_ELEMENT,
                aworm->wcolor); }
   else{ 
 // Inner
    placeItem(aboard,
        BC_USED_BY_WORM, 
        aworm->wormpos[i],
        SYMBOL_WORM_INNER_ELEMENT,aworm->wcolor);}


    i = (i+aworm->cur_lastindex) % (aworm->cur_lastindex+1);
  } while (i != aworm->headindex && aworm->wormpos[i].y != UNUSED_POS_ELEM);


}

bool isInUseByWorm(struct worm* aworm, struct pos new_headpos){
  int i = aworm->headindex;
  bool collision=false;
  do {
    if(new_headpos.x == aworm->wormpos[i].x && new_headpos.y == aworm->wormpos[i].y){
      collision=true;
      break;
    }
    // 3 reverse ringbuffer
    i = (i+aworm->maxindex) % (aworm->maxindex+1);
  } while (i != aworm->headindex && aworm->wormpos[i].x != UNUSED_POS_ELEM);

  return collision;
}

void cleanWormTail(struct board* aboard, struct worm* aworm){
  // Compute tailindex
  int tailindex = (aworm->headindex+1) % (aworm->cur_lastindex+1);

  // Is the array element at tailindex already in use?
  if ( aworm->wormpos[tailindex].y!=UNUSED_POS_ELEM ) {
    placeItem(aboard,BC_FREE_CELL,aworm->wormpos[tailindex],SYMBOL_FREE_CELL, COLP_FREE_CELL);
  }// AUFGABE 8.8 !!!!!!!!!!!!!!!!!!!!!!!!!!
}

void moveWorm(struct board* aboard, struct worm* aworm, enum GameStates* agame_state) {
  //Compute and store new head position according to current heading
  // Get current position of worms head element, but dont store them yet
  struct pos headpos;
  headpos = aworm->wormpos[aworm->headindex];

  headpos.x += aworm->dx;
  headpos.y += aworm->dy;

  // Check if we would leave the display if we move the worm's head according
  // to worm's last direction.
  // We are not allowed to leave the display's window.
  if (headpos.x < 0) {
    *agame_state = WORM_OUT_OF_BOUNDS;
  } else if (headpos.x > getLastColOnBoard(aboard) ) {
    *agame_state = WORM_OUT_OF_BOUNDS;
  } else if (headpos.y < 0) {
    *agame_state = WORM_OUT_OF_BOUNDS;
  } else if (headpos.y > getLastRowOnBoard(aboard) ) {
    *agame_state = WORM_OUT_OF_BOUNDS;
  } else {
    // We will stay within bounds.
    // Check if worms head collides with itself at new position
    // Hitting food is good, hitting barriers or worm elements is bad.
    switch ( getContentAt(aboard,headpos) ) {
    case BC_FOOD_1:
      *agame_state = WORM_GAME_ONGOING;
      // Grow worm according to food item digested
      growWorm(aworm, BONUS_1);
      decrementNumberOfFoodItems(aboard);
      break;
    case BC_FOOD_2:
      growWorm(aworm, BONUS_2);
      decrementNumberOfFoodItems(aboard);
      break;
    case BC_FOOD_3:
      growWorm(aworm, BONUS_3);
      decrementNumberOfFoodItems(aboard);
      break;
    case BC_BARRIER:
      // That's bad: stop game
      *agame_state = WORM_CRASH;
      break;
    case BC_USED_BY_WORM:
      // That's bad: stop game
      *agame_state = WORM_CROSSING;
      break;
    default:
      // Without default case we get a warning message.
      {;} // Do nothing. C syntax dictates some statement, here.
    }

    if(*agame_state == WORM_GAME_ONGOING){
      aworm->headindex++;
      if(aworm->headindex > aworm->cur_lastindex){  //Passt alles wir haben nichts unerelaubtes gerammt
        aworm->headindex = 0;
      }
      //Store new corrdinates of head element in worm structure
      aworm->wormpos[aworm->headindex] = headpos;
    }
  }
}

// Setters
void setWormHeading(struct worm* aworm, enum WormHeading dir) {
  switch(dir) {
  case WORM_UP :
    aworm->dx=0;
    aworm->dy=-1;
    break;
  case WORM_DOWN :
    aworm->dx=0;
    aworm->dy=1;
    break;
  case WORM_LEFT:
    aworm->dx=-1;
    aworm->dy=0;
    break;
  case WORM_RIGHT:
    aworm->dx=1;
    aworm->dy=0;
    break;
  }
}

struct pos getWormHeadPos(struct worm* aworm){
  // structs are passed by value
  // here a copy is returned
  return aworm->wormpos[aworm->headindex];
}

int getWormLength(struct worm* aworm){
  int i = aworm->headindex;
  do {
    i++;
  } while (i != aworm->headindex && aworm->wormpos[i].x != UNUSED_POS_ELEM);

  return i;
}
// Lässt den wurm wachsen !!!!
void growWorm(struct worm* aworm, enum Boni growth) {
  // Play it safe and inhibit surpassing the bound
  if (aworm->cur_lastindex + growth <= aworm->maxindex) {
    aworm->cur_lastindex += growth;
  } else {
    aworm->cur_lastindex = aworm->maxindex;
  }
}
