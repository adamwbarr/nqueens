package co.abarr.nqueens.solver;

import co.abarr.nqueens.Board;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by adam on 06/01/2021.
 */
class BacktrackingTest {
    @Test
    void solveFor_NQueens_ShouldReturnCorrectSolutionsForSize4() {
        Solver solver = Backtracking.N_QUEENS;
        Board board = solver.solveFor(4);
        assertThat(board).isEqualTo(Board.fromString(
            "..x.\n" +
            "x...\n" +
            "...x\n" +
            ".x.."
        ));
    }

    @Test
    void solveFor_NQueens_ShouldReturnCorrectSolutionForSize8() {
        Solver solver = Backtracking.N_QUEENS;
        Board board = solver.solveFor(8);
        assertThat(board).isEqualTo(Board.fromString(
            "x.......\n" +
            "......x.\n" +
            "....x...\n" +
            ".......x\n" +
            ".x......\n" +
            "...x....\n" +
            ".....x..\n" +
            "..x....."
        ));
    }

    @Test
    void solveFor_NQueensExtended_ShouldReturnCorrectSolutionForSize8() {
        Solver solver = Backtracking.N_QUEENS_EXTENDED;
        Board board = solver.solveFor(8);
        assertThat(board).isEqualTo(Board.fromString(
            "....x...\n" +
            "......x.\n" +
            "x.......\n" +
            "...x....\n" +
            ".x......\n" +
            ".......x\n" +
            ".....x..\n" +
            "..x....."
        ));
    }
}