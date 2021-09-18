package com.codeonblue.minesweeper.dto;

public class MarkCellRequest {

    private String cellCurrentStatus;

    public MarkCellRequest() {}

    public MarkCellRequest(String cellCurrentStatus) {
        this.cellCurrentStatus = cellCurrentStatus;
    }

    public String getCellCurrentStatus() {
        return cellCurrentStatus;
    }

    public void setCellCurrentStatus(String cellCurrentStatus) {
        this.cellCurrentStatus = cellCurrentStatus;
    }
}
