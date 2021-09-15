package com.codeonblue.minesweeper.dto;

public class MarkCellResponse extends MarkCellDto {

    public MarkCellResponse() {}

    public MarkCellResponse(CellStatus cellCurrentStatus) { super(cellCurrentStatus); }
}
