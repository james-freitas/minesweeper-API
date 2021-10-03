package com.codeonblue.minesweeper.controller;

import com.codeonblue.minesweeper.dto.CellReveledResponse;
import com.codeonblue.minesweeper.dto.CreatedGameResponse;
import com.codeonblue.minesweeper.dto.MarkCellResponse;
import com.codeonblue.minesweeper.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/games")
    public ResponseEntity<CreatedGameResponse> createGame() {
        final CreatedGameResponse createdGameResponse = gameService.createGame();
        return new ResponseEntity<>(createdGameResponse, HttpStatus.CREATED);
    }

    @PostMapping("/games/{gameId}/cells/{cellId}/reveal")
    public ResponseEntity<CellReveledResponse> getRevealedCells(
            @PathVariable String gameId,
            @PathVariable String cellId
    ) {
        return new ResponseEntity<>(gameService.getReveledCells(gameId, cellId), HttpStatus.OK);
    }

    @PostMapping("/games/{gameId}/cells/{cellId}/mark")
    public ResponseEntity<MarkCellResponse> markCell(
            @PathVariable String gameId,
            @PathVariable String cellId
    ) {
        return new ResponseEntity<>(gameService.markCellAndReturnResponse(gameId, cellId), HttpStatus.OK);
    }
}
