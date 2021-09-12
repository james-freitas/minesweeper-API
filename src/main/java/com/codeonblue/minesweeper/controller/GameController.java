package com.codeonblue.minesweeper.controller;

import com.codeonblue.minesweeper.dto.CreatedGameResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GameController {

    @PostMapping("/games")
    public ResponseEntity<CreatedGameResponse> createGame() {
        final CreatedGameResponse createdGameResponse = new CreatedGameResponse(UUID.randomUUID());
        return new ResponseEntity<>(createdGameResponse, HttpStatus.CREATED);
    }
}
