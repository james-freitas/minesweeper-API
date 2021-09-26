package com.codeonblue.minesweeper.domain;

import java.util.Random;
import java.util.UUID;

public class Game {

    private static final int CELLS_TOTAL = 9;
    private static final int CELLS_PER_ROW = (int) Math.sqrt(CELLS_TOTAL);

    private Cell[] cells = new Cell[CELLS_TOTAL];
    private final UUID id = UUID.randomUUID();

    public Game() {
        fillBombs();
        fillAdjacentBombCells();
    }

    public Game(Cell[] bombCells) {
        this.cells = bombCells;
        fillAdjacentBombCells();
    }

    public Cell[] getCells() {
        return cells;
    }


    private void fillAdjacentBombCells() {
        for (int i = 0; i < CELLS_TOTAL; i++) {
            if (cells[i].hasBomb()) {

                markAdjacentUpperLeftCells(i);

                markAdjacentUpperRightCells(i);

                if (isUpperRow(i) && !isLeftCol(i) && !isRightCol(i)) {
                    cells[i - 1].incNearBombs();
                    cells[i + 1].incNearBombs();
                    cells[i + CELLS_PER_ROW - 1].incNearBombs();
                    cells[i + CELLS_PER_ROW].incNearBombs();
                    cells[i + CELLS_PER_ROW + 1].incNearBombs();
                }
                if (!isUpperRow(i) && isLeftCol(i) && !isBottomRow(i)) {
                    cells[CELLS_PER_ROW - i].incNearBombs();
                    cells[CELLS_PER_ROW - i + 1].incNearBombs();
                    cells[i + 1].incNearBombs();
                    cells[i + CELLS_PER_ROW].incNearBombs();
                    cells[i + CELLS_PER_ROW + 1].incNearBombs();
                }
                
                markAdjacentNotBoarderCells(i);
                
                if (!isUpperRow(i) && !isLeftCol(i) && isRightCol(i) && !isBottomRow(i)) {
                    cells[i - CELLS_PER_ROW - 1].incNearBombs();
                    cells[i - CELLS_PER_ROW].incNearBombs();
                    cells[i - 1].incNearBombs();
                    cells[i + CELLS_PER_ROW - 1].incNearBombs();
                    cells[i + CELLS_PER_ROW].incNearBombs();
                }

                markAdjacentBottomLeftCells(i);

                markAdjacentBottomCells(i);

                markAdjacentBottomRightCells(i);
            }
        }
        clearBombCountingOnBombCells();
    }

    private void markAdjacentBottomCells(int i) {
        if (isBottomRow(i) && !isLeftCol(i) && !isRightCol(i)) {
            cells[i - CELLS_PER_ROW - 1].incNearBombs();
            cells[i - CELLS_PER_ROW].incNearBombs();
            cells[i - CELLS_PER_ROW + 1].incNearBombs();
            cells[i - 1].incNearBombs();
            cells[i + 1].incNearBombs();
        }
    }

    private void markAdjacentNotBoarderCells(int i) {
        if (!isUpperRow(i) && !isLeftCol(i) && !isRightCol(i) && !isBottomRow(i)) {
            cells[i - CELLS_PER_ROW - 1].incNearBombs();
            cells[i - CELLS_PER_ROW].incNearBombs();
            cells[i - CELLS_PER_ROW + 1].incNearBombs();
            cells[i - 1].incNearBombs();
            cells[i + 1].incNearBombs();
            cells[i + CELLS_PER_ROW - 1].incNearBombs();
            cells[i + CELLS_PER_ROW].incNearBombs();
            cells[i + CELLS_PER_ROW + 1].incNearBombs();
        }
    }

    private void markAdjacentBottomRightCells(int i) {
        if (isBottomRow(i) && isRightCol(i)) {
            cells[i - CELLS_PER_ROW - 1].incNearBombs();
            cells[i - CELLS_PER_ROW].incNearBombs();
            cells[i - 1].incNearBombs();
        }
    }

    private void markAdjacentBottomLeftCells(int i) {
        if (isBottomRow(i) && isLeftCol(i)) {
            cells[i - CELLS_PER_ROW].incNearBombs();
            cells[i - CELLS_PER_ROW + 1].incNearBombs();
            cells[i + 1].incNearBombs();
        }
    }

    private void markAdjacentUpperRightCells(int i) {
        if (isUpperRow(i) && isRightCol(i)) {
            cells[i - 1].incNearBombs();
            cells[i + CELLS_PER_ROW - 1].incNearBombs();
            cells[i + CELLS_PER_ROW].incNearBombs();
        }
    }

    private void markAdjacentUpperLeftCells(int i) {
        if (isUpperRow(i) && isLeftCol(i)) {
            cells[i + 1].incNearBombs();
            cells[i + CELLS_PER_ROW].incNearBombs();
            cells[i + CELLS_PER_ROW + 1].incNearBombs();
        }
    }

    private void clearBombCountingOnBombCells() {
        for (int i = 0; i < CELLS_TOTAL - 1; i++) {
            if (cells[i].hasBomb()) {
                cells[i].clearNearBombsCounter();
            }
        }
    }

    private void fillBombs() {
        
        Random random = new Random();
        int bombsAdded = 0;
        
        for (int i = 0; i < CELLS_TOTAL; i++) {
            if (shouldFillBomb(random) && bombsAdded < CELLS_PER_ROW) {
                cells[i] = new Cell(true, i, 0);
                bombsAdded++;
            } else {
                cells[i] = new Cell(false, i, 0);
            }
        }
    }

    private boolean isBottomRow(int position) {
        return CELLS_TOTAL - position <= CELLS_PER_ROW;
    }

    private boolean isRightCol(int position) {
        return (position + 1) % CELLS_PER_ROW == 0;
    }

    private boolean isLeftCol(int position) {
        return position % CELLS_PER_ROW == 0;
    }

    private boolean isUpperRow(int position) {
        return position < CELLS_PER_ROW;
    }
    
    private boolean shouldFillBomb(Random r) {
        return (r.nextInt(CELLS_TOTAL)) % 2 == 0;
    }

    public UUID getId() {
        return id;
    }
}