package co.abarr.nqueens.co.abarr.nqueens.solver;

import co.abarr.nqueens.BoardSet;
import co.abarr.nqueens.rule.Rule;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by adam on 06/01/2021.
 */
class BruteForceTest {
    @Test
    void solveFor_NQueens_ShouldReturnCorrectNumberOfSolutionsForSize4() {
        Solver solver = new BruteForce(Rule.N_QUEENS);
        BoardSet boards = solver.solveFor(4);
        assertThat(boards).hasSize(2);
    }

    @Test
    void solveFor_NQueens_ShouldReturnCorrectNumberOfSolutionsForSize5() {
        Solver solver = new BruteForce(Rule.N_QUEENS);
        BoardSet boards = solver.solveFor(5);
        assertThat(boards).hasSize(10);
    }
}