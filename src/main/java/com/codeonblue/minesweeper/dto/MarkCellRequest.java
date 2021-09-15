package com.codeonblue.minesweeper.dto;

public class MarkCellRequest extends MarkCellDto {

    public MarkCellRequest() {}

    public MarkCellRequest(CellStatus cellCurrentStatus) {
        super(cellCurrentStatus);
    }
}
