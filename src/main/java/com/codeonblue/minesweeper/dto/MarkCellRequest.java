package com.codeonblue.minesweeper.dto;

public class MarkCellRequest {

    public MarkCellRequest() {}

    private CellStatus currentStatus;

    public MarkCellRequest(CellStatus currentStatus) {
        this.currentStatus = currentStatus;
    }

    public CellStatus getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(CellStatus currentStatus) {
        this.currentStatus = currentStatus;
    }
}
