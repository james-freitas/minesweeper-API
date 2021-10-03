package com.codeonblue.minesweeper.service;

import com.codeonblue.minesweeper.domain.GameStatus;
import com.codeonblue.minesweeper.dto.CellReveledResponse;
import com.codeonblue.minesweeper.dto.CellStatus;
import com.codeonblue.minesweeper.dto.CreatedGameResponse;
import com.codeonblue.minesweeper.dto.MarkCellResponse;
import com.codeonblue.minesweeper.exception.ResourceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeonblue.minesweeper.domain.Game.CELLS_TOTAL;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    @DisplayName("Should mark a cell and return its new status")
    void shouldMarkAndReturnNewCellStatus() {

        final GameService gameService = new GameService();
        final CreatedGameResponse game = gameService.createGame();

        final String gameId = game.getGameId();
        final String cellId = "1";

        final MarkCellResponse markCellResponse = gameService.markCellAndReturnResponse(gameId, cellId);

        assertNotNull(markCellResponse);
        assertThat(markCellResponse.getCellCurrentStatus()).isEqualTo(CellStatus.FLAGGED);
    }

    @Test
    @DisplayName("Should fail to mark a cell and return its status when non existing gameId is given")
    void shouldFailToMarkAndReturnCellStatusForNonExistentGame() {

        final GameService gameService = new GameService();

        final String gameId = "non existent game";
        final String cellId = "1";

        assertThrows(ResourceNotFoundException.class, () -> gameService.markCellAndReturnResponse(gameId, cellId));
    }

    @Test
    @DisplayName("Should return reveled cells when the game is in progress")
    void shouldReturnReveledCellsWhenGameIsInProgress() {

        final GameService gameService = new GameService();
        final CreatedGameResponse createdGameResponse = gameService.createGame();

        final String gameId = createdGameResponse.getGameId();
        final String cellId = "1";

        final CellReveledResponse cellReveledResponse = gameService.getReveledCells(gameId, cellId);

        assertNotNull(cellReveledResponse);
        assertNotNull(cellReveledResponse.getGameStatus());
    }

    @Test
    @DisplayName("Should return no reveled cells when the game is over")
    void shouldReturnNoReveledCellsWhenGameOver() {

        final GameService gameService = new GameService();
        final CreatedGameResponse createdGameResponse = gameService.createGame();

        final String gameId = createdGameResponse.getGameId();

        CellReveledResponse cellReveledResponse = new CellReveledResponse();

        for (int i = 0; i < CELLS_TOTAL - 1; i++) {
            cellReveledResponse = gameService.getReveledCells(gameId, String.valueOf(i));
        }

        assertNotNull(cellReveledResponse);
        assertThat(cellReveledResponse.getGameStatus()).isEqualTo(GameStatus.GAME_OVER_PLAYER_LOSES);
        assertTrue(cellReveledResponse.getReveledCells().isEmpty());
    }
}