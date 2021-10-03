package com.codeonblue.minesweeper.service;

import com.codeonblue.minesweeper.domain.Game;
import com.codeonblue.minesweeper.domain.GameStatus;
import com.codeonblue.minesweeper.dto.CellReveledResponse;
import com.codeonblue.minesweeper.dto.CellStatus;
import com.codeonblue.minesweeper.dto.CreatedGameResponse;
import com.codeonblue.minesweeper.dto.MarkCellResponse;
import com.codeonblue.minesweeper.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameService {

    private final Map<String, Game> games = new HashMap<>();
    private static final String CELL_WAS_NOT_FOUND_MESSAGE = "Cell was not found";

    public CreatedGameResponse createGame() {
        final Game game = new Game();
        games.put(game.getId(), game);
        return new CreatedGameResponse(game.getId());
    }

    public CellReveledResponse getReveledCells(String gameId, String cellIdAsString) {

        Map<String, String> reveledCells;

        if (games.containsKey(gameId)) {
            Game game = games.get(gameId);

            final int cellId = validateAndConvert(cellIdAsString);
            final CellReveledResponse cellReveledResponse = new CellReveledResponse();

            if (game.getCells()[cellId].hasBomb()) {
                game.setStatus(GameStatus.GAME_OVER_PLAYER_LOSES);
                reveledCells = new ConcurrentHashMap<>();
            } else {
                reveledCells = game.getReveledCells(cellId);
            }

            cellReveledResponse.setReveledCells(reveledCells);
            cellReveledResponse.setGameStatus(game.getStatus());
            return cellReveledResponse;

        } else {
            throw new ResourceNotFoundException("Game was not found");
        }
    }

    public MarkCellResponse markCellAndReturnResponse(String gameId, String cellIdAsString) {

        if (games.containsKey(gameId)) {
            Game game = games.get(gameId);

            final int cellId = validateAndConvert(cellIdAsString);

            final String currentCellStatus = game.getCells()[cellId].getCellStatus().name();

            if (currentCellStatus.equals(CellStatus.CHECKED.name())) {
                return new MarkCellResponse(game.getCells()[cellId].getCellStatus());
            }

            if (game.getStatus() != GameStatus.GAME_IN_PROGRESS) {
                return new MarkCellResponse(CellStatus.CHECKED);
            }

            switch (currentCellStatus) {
                case "UNCHECKED": game.getCells()[cellId].setCellStatus(CellStatus.FLAGGED);
                    break;
                case "FLAGGED": game.getCells()[cellId].setCellStatus(CellStatus.QUESTION_MARK);
                    break;
                case "QUESTION_MARK": game.getCells()[cellId].setCellStatus(CellStatus.UNCHECKED);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value");
            }
            return new MarkCellResponse(game.getCells()[cellId].getCellStatus());
        } else {
            throw new ResourceNotFoundException("Game was not found");
        }
    }

    private int validateAndConvert(String cellIdAsString) {
        try {
            final int cellId = Integer.parseInt(cellIdAsString);
            if (cellId < 0 || cellId > Game.CELLS_TOTAL - 1) {
                throw new ResourceNotFoundException(CELL_WAS_NOT_FOUND_MESSAGE);
            }
            return cellId;
        } catch (NumberFormatException ex) {
            throw new ResourceNotFoundException(CELL_WAS_NOT_FOUND_MESSAGE);
        }
    }
}
