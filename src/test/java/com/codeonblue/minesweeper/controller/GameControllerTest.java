package com.codeonblue.minesweeper.controller;


import com.codeonblue.minesweeper.dto.CellRevealedResponse;
import com.codeonblue.minesweeper.dto.CreatedGameResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GameController.class)
@AutoConfigureMockMvc
@DisplayName("Minesweeper game controller tests")
class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should create a game and return the game id")
    void shouldCreateMineSweeperGameAndReturnGameId() throws Exception {

        final CreatedGameResponse createdGameResponse = new CreatedGameResponse(UUID.randomUUID());

        mockMvc.perform(post("/games")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.gameId").isNotEmpty());
    }

    @Test
    @DisplayName("Should return revealed cells in an existent game")
    void shouldReturnAllRevealedCells() throws Exception {

        final CellRevealedResponse revealedResponse = generateCellRevealedResponse();

        mockMvc.perform(post("/games/3160c9de-b152-4886-ae52-41f670c493e9/cells/1/reveal")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.revealedCells").isNotEmpty());
    }

    private CellRevealedResponse generateCellRevealedResponse() {
        final CellRevealedResponse cellRevealedResponse = new CellRevealedResponse();
        cellRevealedResponse.setRevealedCells(new HashMap<>());
        return cellRevealedResponse;
    }

}