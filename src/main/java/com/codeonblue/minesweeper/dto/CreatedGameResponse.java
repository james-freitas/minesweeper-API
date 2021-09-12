package com.codeonblue.minesweeper.dto;

import java.util.UUID;

public class CreatedGameResponse {

    private final UUID gameId;

    public CreatedGameResponse(UUID gameId) {
        this.gameId = gameId;
    }

    public UUID getGameId() {
        return gameId;
    }
}
