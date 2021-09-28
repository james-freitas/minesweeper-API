package com.codeonblue.minesweeper.dto;

import java.util.HashMap;
import java.util.Map;

public class CellReveledResponse {

    private Map<String, String> reveledCells = new HashMap<>();

    public Map<String, String> getReveledCells() {
        return reveledCells;
    }

    public void setReveledCells(Map<String, String> reveledCells) {
        this.reveledCells = reveledCells;
    }
}
