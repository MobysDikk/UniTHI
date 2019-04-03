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
#include "messages.h"

    // Place an item onto the curses display.
void placeItem(struct board* aboard, enum BoardCodes board_code, struct pos itempos, chtype symbol, enum ColorPairs color_pair) {

  //  Store item on the display (symbol code)
  move(itempos.y, itempos.x);                         // Move cursor to (y,x)
  attron(COLOR_PAIR(color_pair));     // Start writing in selected color
  addch(symbol);                      // Store symbol on the virtual display
  attroff(COLOR_PAIR(color_pair));    // Stop writing in selected color

  aboard->cells[itempos.y][itempos.x] = board_code;
}

// Getters

int getLastRowOnBoard(struct board* aboard) {
  return aboard->last_row;
}

int getLastColOnBoard(struct board* aboard) {
  return aboard->last_col;
}
int getNumberOfFoodItems(struct board* aboard){
  return aboard->food_items;
}

enum BoardCodes getContentAt(struct board* aboard, struct pos position){
  return aboard->cells[position.y][position.x];
}

void setNumberOfFoodItems(struct board* aboard, int n){
  aboard->food_items = n; // 8.10 !!!!!!
}

void decrementNumberOfFoodItems(struct board* aboard){
  aboard->food_items--;
}

enum ResCodes initializeBoard(struct board* aboard){
  //Check dimensions of the board
  if (COLS < MIN_NUMBER_OF_COLS || LINES < MIN_NUMBER_OF_ROWS + ROWS_RESERVED){
    char buf[100];
    sprintf(buf,"Das Fenster ist zu klein, wir brauchen %dx,%d",MIN_NUMBER_OF_COLS, MIN_NUMBER_OF_ROWS + ROWS_RESERVED);
    showDialog(buf,"Bitte Taste druecken!");
    return RES_FAILED;
  }
    //Maximal index of a row
  aboard->last_row = MIN_NUMBER_OF_ROWS - 1;
  // maximal index of a column
  aboard->last_col = MIN_NUMBER_OF_COLS - 1;

  return RES_OK;
}


enum ResCodes initializeLevel(struct board* aboard) {
  /*@001*/
  struct pos itempos;
  // Fill board and screen buffer with empty cells.
  for (itempos.y = 0; itempos.y/*@002*/ < aboard->last_row ; itempos.y++) {
    for (itempos.x = 0; itempos.x/*@002*/ < aboard->last_col ; itempos.x++) {
      placeItem(aboard,BC_FREE_CELL,itempos,SYMBOL_FREE_CELL,COLP_FREE_CELL);
    }
  }
  // Draw a line in order to separate the message area
  // Note: we cannot use function placeItem() since the message area
  // is outside the board!
  itempos.y = aboard->last_row + 1;
  for (itempos.x=0;/*@003*/ itempos.x < aboard->last_col ; itempos.x++) {
    move(itempos.y, itempos.x);
    attron(COLOR_PAIR(COLP_BARRIER));
    addch(SYMBOL_BARRIER);
    attroff(COLOR_PAIR(COLP_BARRIER));
  }
  // Draw a line to signal the rightmost column of the board.
  itempos.x = aboard->last_col;
  for (itempos.y=0; itempos.y <= aboard->last_row ; itempos.y++) {
    placeItem(aboard,BC_BARRIER,itempos,SYMBOL_BARRIER,COLP_BARRIER);
  }
  // Barriers: use a loop
  itempos.x = (aboard->last_col)/3 -3;
  for (itempos.y = 10; itempos.y < 20; itempos.y++/*@004*/) {
      placeItem(aboard,BC_BARRIER,itempos,SYMBOL_BARRIER,COLP_BARRIER);
  }
  // Right barrier
  // y has to be between 0 and last_row and will be looped
  // x has to be between last_col/3 and last_col and will be static
  itempos.x = (aboard->last_col)/3 + 40;
  for (itempos.y = 6; itempos.y < 14; itempos.y++/*@005*/) {
    placeItem(aboard,BC_BARRIER,itempos,SYMBOL_BARRIER,COLP_BARRIER);
  }

  // Food 1
  itempos.y = 20;
  itempos.x = 3;
  placeItem(aboard,BC_FOOD_1,itempos,SYMBOL_FOOD_1,COLP_FOOD_1);
  itempos.y = 9;
  itempos.x = 6;
  placeItem(aboard,BC_FOOD_1,itempos,SYMBOL_FOOD_1,COLP_FOOD_1);
  // Food 2
  itempos.y = 0;
  itempos.x = 9;
  placeItem(aboard,BC_FOOD_2,itempos,SYMBOL_FOOD_2,COLP_FOOD_2);
  itempos.y = 2;
  itempos.x = 2;
  placeItem(aboard,BC_FOOD_2,itempos,SYMBOL_FOOD_2,COLP_FOOD_2);
  itempos.y = 1;
  itempos.x = 4;
  placeItem(aboard,BC_FOOD_2,itempos,SYMBOL_FOOD_2,COLP_FOOD_2);
  itempos.y = 15;
  itempos.x = 15;
  placeItem(aboard,BC_FOOD_2,itempos,SYMBOL_FOOD_2,COLP_FOOD_2);
  // Food 3
  itempos.y = 15;
  itempos.x = 7;
  placeItem(aboard,BC_FOOD_3,itempos,SYMBOL_FOOD_3,COLP_FOOD_3);
  itempos.y = 20;
  itempos.x = 8;
  placeItem(aboard,BC_FOOD_3,itempos,SYMBOL_FOOD_3,COLP_FOOD_3);
  itempos.y = 24;
  itempos.x = 1;
  placeItem(aboard,BC_FOOD_3,itempos,SYMBOL_FOOD_3,COLP_FOOD_3);
  itempos.y = 12;
  itempos.x = 5;
  placeItem(aboard,BC_FOOD_3,itempos,SYMBOL_FOOD_3,COLP_FOOD_3);

  // Initialize number of food items
  // Attention: must match number of items placed on the board above
  aboard->food_items = 10;
  return RES_OK;
}
