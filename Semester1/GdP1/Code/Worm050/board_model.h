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

#ifndef _BOARD_MODEL_H
#define _BOARD_MODEL_H

#include <curses.h> // bewirkt dass die Datei curses.h in einer vorkonfigurierten menge von systempfaden gesucht wird
#include "worm.h" // bewirt dass die datei worm.h im aktuellen Verzeichniss gesucht wird.

//Positions on the board
struct pos {
    int y;  // y-coordinate (row)
    int x;  // x-coordinate (column)
};

extern void placeItem(struct pos,chtype symbol, enum ColorPairs color_pair); //bestimmtes zeichen an einer bestimmten position auszugeben

//Getters
extern int getLastRow();
extern int getLastCol();

#endif  // #define _BOARD_MODEL_H
