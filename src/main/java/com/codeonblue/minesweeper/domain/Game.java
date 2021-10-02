package com.codeonblue.minesweeper.domain;

import com.codeonblue.minesweeper.dto.CellStatus;

import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Game {

    public static final int CELLS_TOTAL = 9;
    private static final int CELLS_PER_ROW = (int) Math.sqrt(CELLS_TOTAL);

    private Cell[] cells = new Cell[CELLS_TOTAL];
    private final String id = UUID.randomUUID().toString();

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

                markAdjacentUpperCells(i);

                markAdjacentLeftCells(i);

                markAdjacentNotBoarderCells(i);

                markAdjacentRightCells(i);

                markAdjacentBottomLeftCells(i);

                markAdjacentBottomCells(i);

                markAdjacentBottomRightCells(i);
            }
        }
        clearBombCountingOnBombCells();
    }

    private void markAdjacentRightCells(int i) {
        if (!isUpperRow(i) && !isLeftCol(i) && isRightCol(i) && !isBottomRow(i)) {
            cells[i - CELLS_PER_ROW - 1].incNearBombs();
            cells[i - CELLS_PER_ROW].incNearBombs();
            cells[i - 1].incNearBombs();
            cells[i + CELLS_PER_ROW - 1].incNearBombs();
            cells[i + CELLS_PER_ROW].incNearBombs();
        }
    }

    private void markAdjacentLeftCells(int i) {
        if (!isUpperRow(i) && isLeftCol(i) && !isBottomRow(i)) {
            cells[CELLS_PER_ROW - i].incNearBombs();
            cells[CELLS_PER_ROW - i + 1].incNearBombs();
            cells[i + 1].incNearBombs();
            cells[i + CELLS_PER_ROW].incNearBombs();
            cells[i + CELLS_PER_ROW + 1].incNearBombs();
        }
    }

    private void markAdjacentUpperCells(int i) {
        if (isUpperRow(i) && !isLeftCol(i) && !isRightCol(i)) {
            cells[i - 1].incNearBombs();
            cells[i + 1].incNearBombs();
            cells[i + CELLS_PER_ROW - 1].incNearBombs();
            cells[i + CELLS_PER_ROW].incNearBombs();
            cells[i + CELLS_PER_ROW + 1].incNearBombs();
        }
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
                cells[i] = new Cell(true, 0);
                bombsAdded++;
            } else {
                cells[i] = new Cell(false, 0);
            }
        }
    }

  /*
    0  0  0
    2  3  2
    x  x  x

    0  1  2
    3  4  5
    6  7  8

    2  x  2  x
    2  x  2  x
    1  1  1  1
    0  0  0  0

    */

    public Map<String,String> getReveledCells(int cellId) {
        Map<String, String> revealedCells = new ConcurrentHashMap<>();

        if (cells[cellId].getCellStatus() == CellStatus.CHECKED) {
            return revealedCells;
        }

        cells[cellId].setCellStatus(CellStatus.CHECKED);
        revealedCells.put(String.valueOf(cellId), String.valueOf(cells[cellId].getNearBombs()));

        if (cells[cellId].hasBomb()) {
            return revealedCells;
        }

        return checkAdjacentCells(cellId, revealedCells);
    }

    private Map<String, String> checkAdjacentCells(int cellId, Map<String, String> revealedCells) {

        int counter = cellId;

        while (!isLeftCol(counter)) {
            counter--;
            if (!cells[counter].hasBomb()) {
                cells[counter].setCellStatus(CellStatus.CHECKED);

                revealedCells.put(String.valueOf(counter),
                        String.valueOf(cells[counter].getNearBombs()));

                if (!isUpperRow(counter)) {
                    checkAdjacentCells(counter - CELLS_PER_ROW, revealedCells);
                }
            } else {
                break;
            }
        }

        counter = cellId;

        while (!isRightCol(counter)) {
            counter++;
            if (!cells[counter].hasBomb()) {
                cells[counter].setCellStatus(CellStatus.CHECKED);

                revealedCells.put(String.valueOf(counter),
                        String.valueOf(cells[counter].getNearBombs()));

                if (!isBottomRow(counter)) {
                    checkAdjacentCells(counter + CELLS_PER_ROW, revealedCells);
                }
            } else {
                break;
            }
        }
        return revealedCells;
    }

    /*
    0 1 2
    3 4 5
    6 7 8

     */

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
        return Math.random() < 0.33;
    }

    public String getId() {
        return id;
    }
}