package co.abarr.nqueens.solver;

import co.abarr.nqueens.Board;
import co.abarr.nqueens.BoardSet;
import co.abarr.nqueens.rule.Rule;

/**
 * The solution described here: https://www.geeksforgeeks.org/n-queen-problem-backtracking-3.
 * <p>
 * Created by adam on 06/01/2021.
 */
class Backtracking implements Solver {
    @Override
    public BoardSet solveFor(int width) {
        return solutionsFor(Board.of(width), 0);
    }

    private BoardSet solutionsFor(Board board, int column) {
        if (column == board.width()) {
            return BoardSet.of(board);
        } else {
            BoardSet solutions = BoardSet.empty();
            for (int row = 0; row < board.width(); row++) {
                Board next = board.occupy(row, column);
                if (Rule.NO_CONFLICTS.isSatisfiedBy(next)) {
                    solutions = solutions.plus(solutionsFor(next, column + 1));
                }
            }
            return solutions;
        }
    }
}
