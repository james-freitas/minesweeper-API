package com.codeonblue.minesweeper.controller;


import com.codeonblue.minesweeper.dto.CellReveledResponse;
import com.codeonblue.minesweeper.dto.CellStatus;
import com.codeonblue.minesweeper.dto.CreatedGameResponse;
import com.codeonblue.minesweeper.dto.MarkCellResponse;
import com.codeonblue.minesweeper.service.GameService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GameService gameService;

    @Test
    @DisplayName("Should create a game and return the game id")
    void shouldCreateMineSweeperGameAndReturnGameId() throws Exception {

        final CreatedGameResponse createdGameResponse = new CreatedGameResponse(UUID.randomUUID().toString());

        given(gameService.createGame()).willReturn(createdGameResponse);

        mockMvc.perform(post("/games")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.gameId").isNotEmpty());

        verify(gameService, times(1)).createGame();
    }

    @Test
    @DisplayName("Should fail to return reveled cells when game does not exist")
    void shouldFailToReturnReveledCellsOnNonExistentGame() throws Exception {

        final CellReveledResponse revealedResponse = generateCellRevealedResponse();
        final String gameId = "notFound";
        final String cellId = "1";
        final String url = "/games/" + gameId + "/cells/" + cellId + "/reveal";

        given(gameService.getReveledCells(gameId, cellId)).willReturn(revealedResponse);

        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reveledCells").isNotEmpty());

        verify(gameService, times(1)).getReveledCells(gameId, cellId);
    }

    @Test
    @DisplayName("Should return revealed cells in an existent game")
    void shouldReturnAllRevealedCells() throws Exception {

        final CellReveledResponse revealedResponse = generateCellRevealedResponse();
        final String gameId = "3160c9de-b152-4886-ae52-41f670c493e9";
        final String cellId = "1";
        final String url = "/games/" + gameId + "/cells/" + cellId + "/reveal";

        given(gameService.getReveledCells(gameId, cellId)).willReturn(revealedResponse);

        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reveledCells").isNotEmpty());

        verify(gameService, times(1)).getReveledCells(gameId, cellId);
    }

    private CellReveledResponse generateCellRevealedResponse() {
        final Map<String, String> revealedCells = new HashMap<>();
        revealedCells.put("1", "1");
        final CellReveledResponse cellReveledResponse = new CellReveledResponse();
        cellReveledResponse.setReveledCells(revealedCells);
        return cellReveledResponse;
    }

    @Test
    @DisplayName("Should mark cell as FLAGGED when current status is UNCHECKED in existent game")
    void shouldMarkCellAsFlagged() throws Exception {

        final String gameId = "3160c9de-b152-4886-ae52-41f670c493e9";
        final String cellId = "1";

        final MarkCellResponse markCellResponse = new MarkCellResponse(CellStatus.FLAGGED);

        given(gameService.markCellAndReturnResponse(gameId, cellId)).willReturn(markCellResponse);

        mockMvc.perform(post("/games/3160c9de-b152-4886-ae52-41f670c493e9/cells/1/mark"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cellCurrentStatus").isNotEmpty())
                .andExpect(jsonPath("$.cellCurrentStatus").value("FLAGGED"));
    }

    @Test
    @DisplayName("Should mark cell as QUESTION_MARK when current status is FLAGGED in existent game")
    void shouldMarkCellAsQuestionMark() throws Exception {

        final String gameId = "3160c9de-b152-4886-ae52-41f670c493e9";
        final String cellId = "1";

        final MarkCellResponse markCellResponse = new MarkCellResponse(CellStatus.QUESTION_MARK);

        given(gameService.markCellAndReturnResponse(gameId, cellId)).willReturn(markCellResponse);

        mockMvc.perform(post("/games/3160c9de-b152-4886-ae52-41f670c493e9/cells/1/mark"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cellCurrentStatus").isNotEmpty())
                .andExpect(jsonPath("$.cellCurrentStatus").value("QUESTION_MARK"));
    }

    @Test
    @DisplayName("Should mark cell as UNCHECKED when current status is QUESTION_MARK in existent game")
    void shouldMarkCellAsUnchecked() throws Exception {

        final String gameId = "3160c9de-b152-4886-ae52-41f670c493e9";
        final String cellId = "1";

        final MarkCellResponse markCellResponse = new MarkCellResponse(CellStatus.UNCHECKED);

        given(gameService.markCellAndReturnResponse(gameId, cellId)).willReturn(markCellResponse);

        mockMvc.perform(post("/games/3160c9de-b152-4886-ae52-41f670c493e9/cells/1/mark"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cellCurrentStatus").isNotEmpty())
                .andExpect(jsonPath("$.cellCurrentStatus").value("UNCHECKED"));
    }
}