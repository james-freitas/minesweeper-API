package com.codeonblue.minesweeper.service;

import com.codeonblue.minesweeper.dto.CellReveledResponse;
import com.codeonblue.minesweeper.dto.CreatedGameResponse;
import com.codeonblue.minesweeper.exception.ResourceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GameServiceTest {

    @Test
    @DisplayName("Should create a game a return its id")
    void shouldCreateGameSuccessfully() {
        final GameService gameService = new GameService();
        final CreatedGameResponse game = gameService.createGame();
        assertNotNull(game);
    }

    @Test
    @DisplayName("Should return a map of reveled cells")
    void shouldReturnRevealedCellsMap() {

        final GameService gameService = new GameService();
        final CreatedGameResponse game = gameService.createGame();

        final String gameId = game.getGameId();
        final String cellId = "1";

        final CellReveledResponse reveledCellsResponse = gameService.getReveledCells(gameId, cellId);

        assertNotNull(reveledCellsResponse.getReveledCells());
    }

    @Test
    @DisplayName("Should fail to return map of reveled cells when non existing gameId is given")
    void shouldFailToReturnRevealedCellsMapForNonExistentGame() {

        final GameService gameService = new GameService();

        final String gameId = "non existent game";
        final String cellId = "1";

        assertThrows(ResourceNotFoundException.class, () -> gameService.getReveledCells(gameId, cellId));
    }

    @Test
    @DisplayName("Should fail to return map of reveled cells when non existing cellId is given")
    void shouldFailToReturnRevealedCellsMapForNonExistentCell() {

        final GameService gameService = new GameService();
        final CreatedGameResponse game = gameService.createGame();

        final String gameId = game.getGameId();
        final String cellId = "999";

        assertThrows(ResourceNotFoundException.class, () -> gameService.getReveledCells(gameId, cellId));
    }

    @Test
    @DisplayName("Should fail to return map of reveled cells when invalid cellId is given")
    void shouldFailToReturnRevealedCellsMapForInvalidCell() {

        final GameService gameService = new GameService();
        final CreatedGameResponse game = gameService.createGame();

        final String gameId = game.getGameId();
        final String cellId = "invalidCell";

        assertThrows(ResourceNotFoundException.class, () -> gameService.getReveledCells(gameId, cellId));
    }
}