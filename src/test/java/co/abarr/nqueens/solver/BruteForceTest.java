package co.abarr.nqueens.solver;

import co.abarr.nqueens.Board;
import co.abarr.nqueens.BoardSet;
import co.abarr.nqueens.rule.Rule;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by adam on 06/01/2021.
 */
class BruteForceTest {
    @Test
    void solveFor_NQueens_ShouldReturnCorrectSolutionsForSize4() {
        Solver solver = new BruteForce(Rule.N_QUEENS);
        Board board = solver.solveFor(4);
        assertThat(board).isEqualTo(Board.fromString(
            "..x.\n" +
            "x...\n" +
            "...x\n" +
            ".x.."
        ));
    }
}