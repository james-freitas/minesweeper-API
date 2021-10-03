package com.codeonblue.minesweeper.service;

import com.codeonblue.minesweeper.domain.Game;
import com.codeonblue.minesweeper.exception.ResourceNotFoundException;

public class MarkCellInput {

    private final String gameId;
    private final int cellId;
    private final String cellCurrentStatus;

    public MarkCellInput(String gameId, String cellIdAsString, String cellCurrentStatus) {
        try {
            this.cellId = Integer.parseInt(cellIdAsString);
            if (cellId < 0 || cellId > Game.CELLS_TOTAL - 1) {
                throw new ResourceNotFoundException("Cell was not found");
            }

        } catch (NumberFormatException ex) {
            throw new ResourceNotFoundException("Cell was not found");
        }
        this.gameId = gameId;
        this.cellCurrentStatus = cellCurrentStatus;
    }

    public String getGameId() {
        return gameId;
    }

    public String getCellCurrentStatus() {
        return cellCurrentStatus;
    }

    public int getCellId() {
        return cellId;
    }
}
