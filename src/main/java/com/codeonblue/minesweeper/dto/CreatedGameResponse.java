package com.codeonblue.minesweeper.dto;

public class CreatedGameResponse {

    private final String gameId;

    public CreatedGameResponse(String gameId) {
        this.gameId = gameId;
    }

    public String getGameId() {
        return gameId;
    }
}
