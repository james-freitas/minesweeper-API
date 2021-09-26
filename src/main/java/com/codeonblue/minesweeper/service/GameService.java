package com.codeonblue.minesweeper.service;

import com.codeonblue.minesweeper.domain.Game;
import com.codeonblue.minesweeper.dto.CreatedGameResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameService {

    public CreatedGameResponse createGame() {
        final Game game = new Game();

        final CreatedGameResponse createdGameResponse = new CreatedGameResponse(UUID.randomUUID());
        return createdGameResponse;
    }
}
