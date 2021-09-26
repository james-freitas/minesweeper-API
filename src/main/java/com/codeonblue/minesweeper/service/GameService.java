package com.codeonblue.minesweeper.service;

import com.codeonblue.minesweeper.domain.Game;
import com.codeonblue.minesweeper.dto.CreatedGameResponse;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    public CreatedGameResponse createGame() {
        final Game game = new Game();
        return new CreatedGameResponse(game.getId());
    }
}
