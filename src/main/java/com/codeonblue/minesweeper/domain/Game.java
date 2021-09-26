package com.codeonblue.minesweeper.domain;

import java.util.Random;

public class Game {

    private static final int CELLS_TOTAL = 9;
    private static final int CELLS_PER_ROW = (int) Math.sqrt(CELLS_TOTAL);
    private static final int BOMBS_TOTAL = 3;

    public Cell[] getCells() {
        return cells;
    }

    public boolean[] getBombs() {
        return bombs;
    }

    private Cell[] cells = new Cell[CELLS_TOTAL];
    private boolean[] bombs = new boolean[BOMBS_TOTAL];

    /*
      2    2x   2x
      2    2x   3
      1    1    1
    */

    public Game() { }

    public Game(Cell[] bombCells) {
        this.cells = bombCells;

        fillAdjacentBombCells();
    }


    private void fillAdjacentBombCells() {
        for (int i = 0; i < CELLS_TOTAL; i++) {
            if (cells[i].hasBomb() && isUpperRow(i) && isLeftCol(i) && !cells[i + 1].hasBomb()) {
                cells[i + 1].incNearBombs();
            }
//            if (cells[i].hasBomb() && isUpperRow(i) && isRightCol(i) && !cells[i + 1].hasBomb()) {
//                cells[i + 1].incNearBombs();
//            }
        }
    }



    private void fillBombs() {

        for (int i = 0; i < CELLS_TOTAL; i++) {
            if (i == 1 || i == 2 || i == 4) {
                cells[i] = new Cell(true, i, 0);
            } else {
                cells[i] = new Cell(false, i, 0);
            }
        }

//        for (int i = 0; i < CELLS_TOTAL; i++) {
//            if (cells[i].hasBomb()) {
//                if (!isUpperRow(i) && !isLeftCol(i) && !cells[i - CELLS_PER_ROW - 1].hasBomb()) {
//                    cells[i - CELLS_PER_ROW - 1].incNearBombs();
//                }
//
//                if (!isUpperRow(i) && !cells[i - CELLS_PER_ROW].hasBomb()) {
//                    cells[i - CELLS_PER_ROW].incNearBombs();
//                }
//
//                if (!isUpperRow(i) && !isRightCol(i) && !cells[i - CELLS_PER_ROW + 1].hasBomb()) {
//                    cells[i - CELLS_PER_ROW + 1].incNearBombs();
//                }
//
//                if (!isLeftCol(i) && !cells[i - 1].hasBomb()) {
//                    cells[i - 1].incNearBombs();
//                }
//
//                if (!isRightCol(i) && !cells[i + 1].hasBomb()) {
//                    cells[i + 1].incNearBombs();
//                }
//
//                if (!isBottomRow(i) && !isLeftCol(i) && !cells[i + CELLS_PER_ROW - 1].hasBomb()) {
//                    cells[i + CELLS_PER_ROW - 1].incNearBombs();
//                }
//
//                if (!isBottomRow(i) && !cells[i + CELLS_PER_ROW].hasBomb()) {
//                    cells[i + CELLS_PER_ROW].incNearBombs();
//                }
//
//                if (!isBottomRow(i) && !isRightCol(i) && !cells[i + CELLS_PER_ROW + 1].hasBomb()) {
//                    cells[i + CELLS_PER_ROW + 1].incNearBombs();
//                }
//            }
//        }

    }

    private boolean isBottomRow(int position) {
        return position < CELLS_TOTAL - CELLS_PER_ROW;
    }

    private boolean isRightCol(int position) {
        return (position + 1) % CELLS_TOTAL == 0;
    }

    private boolean isLeftCol(int position) {
        return position % CELLS_PER_ROW == 0;
    }

    private boolean isUpperRow(int position) {
        return position < CELLS_PER_ROW;
    }

    private boolean isBombNear(int position) {
        return position < CELLS_TOTAL - CELLS_PER_ROW &&
                (cells[position + 1].hasBomb() ||
                cells[position + CELLS_PER_ROW].hasBomb());
    }

    private boolean shouldFillBomb(Random r) {
        return (r.nextInt(16)) % 2 == 0;
    }
}

// https://www.youtube.com/watch?v=4X8U8RXhyk4

