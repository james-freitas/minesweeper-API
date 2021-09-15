package com.codeonblue.minesweeper.dto;

public class MarkCellResponse {

    private CellStatus cellStatus;

    public MarkCellResponse(CellStatus cellStatus) {
        this.cellStatus = cellStatus;
    }

    public CellStatus getCellStatus() {
        return cellStatus;
    }

    public void setCellStatus(CellStatus cellStatus) {
        this.cellStatus = cellStatus;
    }
}
