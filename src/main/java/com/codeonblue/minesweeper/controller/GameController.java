package com.codeonblue.minesweeper.controller;

import com.codeonblue.minesweeper.dto.CellRevealedResponse;
import com.codeonblue.minesweeper.dto.CellStatus;
import com.codeonblue.minesweeper.dto.CreatedGameResponse;
import com.codeonblue.minesweeper.dto.MarkCellRequest;
import com.codeonblue.minesweeper.dto.MarkCellResponse;
import com.codeonblue.minesweeper.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<CellRevealedResponse> getRevealedCells(
            @PathVariable String gameId,
            @PathVariable String cellId
    ) {
        return new ResponseEntity<>(gameService.getRevealedCells(gameId, cellId), HttpStatus.OK);
    }

    @PostMapping("/games/{gameId}/cells/{cellId}/mark")
    public ResponseEntity<MarkCellResponse> markCell(
            @PathVariable String gameId,
            @PathVariable String cellId,
            @RequestBody MarkCellRequest markCellRequest
    ) {
        CellStatus cellStatus;

        switch (markCellRequest.getCellCurrentStatus()) {
            case "UNCHECKED": cellStatus = CellStatus.FLAGGED;
                break;
            case "FLAGGED": cellStatus = CellStatus.QUESTION_MARK;
                break;
            case "QUESTION_MARK": cellStatus = CellStatus.UNCHECKED;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + markCellRequest.getCellCurrentStatus());
        }

        final MarkCellResponse markCellResponse = new MarkCellResponse(cellStatus);
        return new ResponseEntity<>(markCellResponse, HttpStatus.OK);
    }
}
