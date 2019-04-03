// A simple variant of the game Snake
//
// Used for teaching in classes
//
// Author:
// Franz Regensburger
// Ingolstadt University of Applied Sciences
// (C) 2011
//
// The board model

#include <curses.h>
#include "worm.h"
#include "board_model.h"


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


