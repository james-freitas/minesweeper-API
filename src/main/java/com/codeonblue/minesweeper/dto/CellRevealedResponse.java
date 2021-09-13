package com.codeonblue.minesweeper.dto;

import java.util.HashMap;
import java.util.Map;

public class CellRevealedResponse {

    private Map<String, Integer> revealedCells = new HashMap<>();

    public Map<String, Integer> getRevealedCells() {
        return revealedCells;
    }

    public void setRevealedCells(Map<String, Integer> revealedCells) {
        this.revealedCells = revealedCells;
    }
}
