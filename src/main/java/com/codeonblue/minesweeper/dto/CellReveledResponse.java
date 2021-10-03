package com.codeonblue.minesweeper.dto;

import com.codeonblue.minesweeper.domain.GameStatus;

import java.util.HashMap;
import java.util.Map;

public class CellReveledResponse {

    private Map<String, String> reveledCells = new HashMap<>();
    private GameStatus gameStatus;

    public Map<String, String> getReveledCells() {
        return reveledCells;
    }

    public void setReveledCells(Map<String, String> reveledCells) {
        this.reveledCells = reveledCells;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }
}
