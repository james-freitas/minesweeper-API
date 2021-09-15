package com.codeonblue.minesweeper.dto;

public class MarkCellDto {

    protected CellStatus cellCurrentStatus;

    public MarkCellDto() {}

    public MarkCellDto(CellStatus cellCurrentStatus) {
        this.cellCurrentStatus = cellCurrentStatus;
    }

    public CellStatus getCellCurrentStatus() {
        return cellCurrentStatus;
    }

    public void setCellCurrentStatus(CellStatus cellCurrentStatus) {
        this.cellCurrentStatus = cellCurrentStatus;
    }
}
