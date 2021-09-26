package com.codeonblue.minesweeper.domain;

import com.codeonblue.minesweeper.dto.CellStatus;

public class Cell {

    private boolean hasBomb;
    private final int id;

    private CellStatus cellStatus;
    private int nearBombs;

    public Cell(boolean hasBomb, int id, int nearBombs) {
        this.hasBomb = hasBomb;
        this.id = id;
        this.cellStatus = CellStatus.UNCHECKED;
        this.nearBombs = nearBombs;
    }

    public boolean hasBomb() {
        return hasBomb;
    }

    public void setHasBomb(boolean hasBomb) {
        this.hasBomb = hasBomb;
    }

    public CellStatus getCellStatus() {
        return cellStatus;
    }

    public void setCellStatus(CellStatus cellStatus) {
        this.cellStatus = cellStatus;
    }

    public int getNearBombs() {
        return nearBombs;
    }

    public void incNearBombs() {
        this.nearBombs = nearBombs + 1;
    }

    public void clearNearBombsCounter() { this.nearBombs = 0; }

    public int getId() {
        return id;
    }
}
