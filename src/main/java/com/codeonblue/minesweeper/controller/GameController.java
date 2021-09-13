package com.codeonblue.minesweeper.controller;

import com.codeonblue.minesweeper.dto.CellRevealedResponse;
import com.codeonblue.minesweeper.dto.CreatedGameResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<CellRevealedResponse> getRevealedCells() {
        final Map<String, Integer> revealedCells = new HashMap<>();
        revealedCells.put("1",1);
        final CellRevealedResponse cellRevealedResponse = new CellRevealedResponse();
        cellRevealedResponse.setRevealedCells(revealedCells);
        return new ResponseEntity<>(cellRevealedResponse, HttpStatus.OK);
    }
}
