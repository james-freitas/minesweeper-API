package com.codeonblue.minesweeper.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GameTest {

    private static final int BOMBS_TOTAL = 4;

    @Test
    void shouldCreateNewGame() {
        final Game game = new Game();
        assertNotNull(game);
    }

//    @Test
//    void shouldCreateGameUsingAllBombsSupported() {
//        final Game game = new Game();
//        assertThat(game.getBombs().length).isEqualTo(BOMBS_TOTAL);
//    }

    @Test
    void shouldFillTheCellsWithTheNumberOfAdjacentBombsInANineCellsGame() {

    /*
      x    1    0
      1    1    0
      0    0    0
    */

        Cell[] expectedCells = new Cell[9];
        expectedCells[0] = new Cell(true, 0, 0);
        expectedCells[1] = new Cell(false, 1, 1);
        expectedCells[2] = new Cell(false, 2, 0);
        expectedCells[3] = new Cell(false, 3, 1);
        expectedCells[4] = new Cell(false, 4, 1);

        expectedCells[5] = new Cell(false, 5, 0);
        expectedCells[6] = new Cell(false, 6, 0);
        expectedCells[7] = new Cell(false, 7, 0);
        expectedCells[8] = new Cell(false, 8, 0);

        Cell[] inputCells = new Cell[9];
        inputCells[0] = new Cell(false, 0, 0);
        inputCells[1] = new Cell(true, 1, 0);  // b = true
        inputCells[2] = new Cell(true, 2, 0); // b = true

        inputCells[3] = new Cell(false, 3, 0);
        inputCells[4] = new Cell(true, 4, 0); // b = true
        inputCells[5] = new Cell(false, 5, 0);
        inputCells[6] = new Cell(false, 6, 0);
        inputCells[7] = new Cell(false, 7, 0);
        inputCells[8] = new Cell(false, 8, 0);

        final Game game = new Game(inputCells);
        assertThat(game.getCells()[0].getNearBombs()).isEqualTo(expectedCells[0].getNearBombs());
//        assertThat(game.getCells()[1].getNearBombs()).isEqualTo(expectedCells[1].getNearBombs());
//        assertThat(game.getCells()[2].getNearBombs()).isEqualTo(expectedCells[2].getNearBombs());
//        assertThat(game.getCells()[3].getNearBombs()).isEqualTo(expectedCells[3].getNearBombs());
//
//        assertThat(game.getCells()[4].getNearBombs()).isEqualTo(expectedCells[4].getNearBombs());
//        assertThat(game.getCells()[5].getNearBombs()).isEqualTo(expectedCells[5].getNearBombs());
//        assertThat(game.getCells()[6].getNearBombs()).isEqualTo(expectedCells[6].getNearBombs());
//        assertThat(game.getCells()[7].getNearBombs()).isEqualTo(expectedCells[7].getNearBombs());
//        assertThat(game.getCells()[8].getNearBombs()).isEqualTo(expectedCells[8].getNearBombs());
    }
}