package com.codeonblue.minesweeper.service;

import com.codeonblue.minesweeper.domain.Game;
import com.codeonblue.minesweeper.dto.CellReveledResponse;
import com.codeonblue.minesweeper.dto.CreatedGameResponse;
import com.codeonblue.minesweeper.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GameService {

    private Map<String, Game> games = new HashMap<>();

    public CreatedGameResponse createGame() {
        final Game game = new Game();
        games.put(game.getId(), game);
        return new CreatedGameResponse(game.getId());
    }

    public CellReveledResponse getReveledCells(String gameId, String cellIdAsString) {

        Map<String, String> reveledCells;

        if (games.containsKey(gameId)) {
            Game game = games.get(gameId);

            try {
                final int cellId = Integer.parseInt(cellIdAsString);
                if (cellId < 0 || cellId > Game.CELLS_TOTAL - 1) {
                    throw new ResourceNotFoundException("Cell was not found");
                }
                reveledCells = game.getReveledCells(cellId);
            } catch (NumberFormatException ex) {
                throw new ResourceNotFoundException("Cell was not found");
            }

        } else {
            throw new ResourceNotFoundException("Game was not found");
        }
        final CellReveledResponse cellReveledResponse = new CellReveledResponse();
        cellReveledResponse.setReveledCells(reveledCells);
        return cellReveledResponse;
    }
}
