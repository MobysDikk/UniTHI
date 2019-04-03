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

#ifndef _WORM_MODEL_H
#define _WORM_MODEL_H

#include <stdbool.h>
#include "worm.h"
#include "board_model.h"

//Getters
 struct pos getWormHeadPos(); // vorwärtsdeklaration



// A worm structure
struct worm {
    
    int maxindex;// Last usable index into the array pointed to by wormpos
    int headindex; // An index into the array for the head position of the worm
    //0 <- headindex <- maxindex
    struct pos wormpos [WORM_LENGTH];// Array of the x,y postions of all elements    
   // struct pos headpos [aworm->headindex];


// The current heading of the worm
// THese Are offsets from the set {-1,0,+1}
    int dx;
    int dy;

    //Color of the worm
    enum ColorPairs wcolor;
};

//Directions for the worm
enum WormHeading {
     WORM_UP,
     WORM_DOWN,
     WORM_LEFT,
     WORM_RIGHT,
   };   

extern enum ResCodes initializeWorm(struct worm* aworm, int len_max, struct pos headpos,enum WormHeading dir, enum ColorPairs color); //start werte des Wurms

extern void showWorm(struct worm* aworm);
extern void cleanWormTail(struct worm* aworm);
extern void moveWorm(struct worm* aworm ,enum GameStates* agame_state);     //bewegt den wurm um einen schritt
extern bool isInUseByWorm(struct worm* aworm , struct pos new_headpos); // JA ODER NEIN

//Setters
extern void setWormHeading(struct worm* aworm, enum WormHeading dir)       ;// richtung 0123 in xy delta
//extern void getWormHeadPos(struct worm* aworm)
#endif  // #define _WORM_MODEL_H
