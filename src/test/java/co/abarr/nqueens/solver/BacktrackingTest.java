package co.abarr.nqueens.solver;

import co.abarr.nqueens.Board;
import co.abarr.nqueens.BoardSet;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by adam on 06/01/2021.
 */
class BacktrackingTest {
    @Test
    void solveFor_NQueens_ShouldReturnCorrectSolutionsForSize4() {
        Solver solver = new Backtracking();
        BoardSet boards = solver.solveFor(4);
        assertThat(boards).containsExactly(
            Board.fromString(
                ".x..\n" +
                "...x\n" +
                "x...\n" +
                "..x."
            ),
            Board.fromString(
                "..x.\n" +
                "x...\n" +
                "...x\n" +
                ".x.."
            )
        );
    }

    @Test
    void solveFor_NQueens_ShouldReturnCorrectNumberOfSolutionsForSize8() {
        Solver solver = new Backtracking();
        BoardSet boards = solver.solveFor(8);
        assertThat(boards).hasSize(92);
    }
}