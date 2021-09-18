package com.codeonblue.minesweeper.controller;

import com.codeonblue.minesweeper.dto.CellRevealedResponse;
import com.codeonblue.minesweeper.dto.CellStatus;
import com.codeonblue.minesweeper.dto.CreatedGameResponse;
import com.codeonblue.minesweeper.dto.MarkCellRequest;
import com.codeonblue.minesweeper.dto.MarkCellResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class GameController {

    @PostMapping("/games")
    public ResponseEntity<CreatedGameResponse> createGame() {
        final CreatedGameResponse createdGameResponse = new CreatedGameResponse(UUID.randomUUID());
        return new ResponseEntity<>(createdGameResponse, HttpStatus.CREATED);
    }

    @PostMapping("/games/{gameId}/cells/{cellId}/reveal")
    public ResponseEntity<CellRevealedResponse> getRevealedCells(
            @PathVariable String gameId,
            @PathVariable String cellId
    ) {
        final Map<String, Integer> revealedCells = new HashMap<>();
        revealedCells.put("1", 1);
        final CellRevealedResponse cellRevealedResponse = new CellRevealedResponse();
        cellRevealedResponse.setRevealedCells(revealedCells);
        return new ResponseEntity<>(cellRevealedResponse, HttpStatus.OK);
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
