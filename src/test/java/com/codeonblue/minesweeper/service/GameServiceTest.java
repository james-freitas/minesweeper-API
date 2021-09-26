package com.codeonblue.minesweeper.service;

import com.codeonblue.minesweeper.dto.CreatedGameResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class GameServiceTest {

    @Test
    @DisplayName("Should create a game a return its id")
    void shouldCreateGameSuccessfully() {
        final GameService gameService = new GameService();
        final CreatedGameResponse game = gameService.createGame();
        assertNotNull(game);
    }
}