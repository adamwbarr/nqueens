package co.abarr.nqueens.solver;

import co.abarr.nqueens.BoardSet;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by adam on 06/01/2021.
 */
class SolverTest {
    @Test
    void nQueens_ForWidth8_ShouldReturnCorrectNumberOfSolutions() {
        BoardSet boards = Solver.N_QUEENS.solveFor(8);
        assertThat(boards).hasSize(92);
    }

    @Test
    void nQueensExtended_ForWidth8_ShouldReturnCorrectNumberOfSolutions() {
        BoardSet boards = Solver.N_QUEENS_EXTENDED.solveFor(8);
        assertThat(boards).hasSize(8);
    }
}
