package com.codeonblue.minesweeper.service;

import com.codeonblue.minesweeper.dto.CellStatus;
import com.codeonblue.minesweeper.exception.ResourceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MarkCellInputTest {

    @Test
    @DisplayName("Should fail to create MarkCellInput when invalid cellId is given")
    void shouldFailToCreateMarkCellInputForInvalidCell() {

        final String gameId = "validGameId";
        final String cellId = "invalidCell";
        final String currentCellStatus = CellStatus.UNCHECKED.name();

        assertThrows(ResourceNotFoundException.class, () -> new MarkCellInput(gameId, cellId, currentCellStatus));
    }

    @Test
    @DisplayName("Should fail to mark a cell and return its status when non existing cellId is given")
    void shouldFailToCreateMarkCellInputForNonExistentCell() {

        final String gameId = "cellId";
        final String cellId = "999";
        final String currentCellStatus = CellStatus.UNCHECKED.name();

        assertThrows(ResourceNotFoundException.class, () -> new MarkCellInput(gameId, cellId, currentCellStatus));
    }
}