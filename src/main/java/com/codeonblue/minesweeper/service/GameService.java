package com.codeonblue.minesweeper.service;

import com.codeonblue.minesweeper.domain.Game;
import com.codeonblue.minesweeper.dto.CellRevealedResponse;
import com.codeonblue.minesweeper.dto.CreatedGameResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GameService {

    public CreatedGameResponse createGame() {
        final Game game = new Game();
        return new CreatedGameResponse(game.getId());
    }

    public CellRevealedResponse getRevealedCells(String gameId, String cellId) {
        final Map<String, Integer> revealedCells = new HashMap<>();
        revealedCells.put("1", 1);
        final CellRevealedResponse cellRevealedResponse = new CellRevealedResponse();
        cellRevealedResponse.setRevealedCells(revealedCells);
        return cellRevealedResponse;
    }
}
