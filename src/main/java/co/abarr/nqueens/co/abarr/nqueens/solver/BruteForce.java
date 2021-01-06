package co.abarr.nqueens.co.abarr.nqueens.solver;

import co.abarr.nqueens.Board;
import co.abarr.nqueens.BoardSet;
import co.abarr.nqueens.rule.Rule;

import java.util.Objects;

/**
 * Created by adam on 06/01/2021.
 */
class BruteForce implements Solver {
    private final Rule rule;

    public BruteForce(Rule rule) {
        this.rule = Objects.requireNonNull(rule);
    }

    @Override
    public BoardSet solveFor(int width) {
        return solve(Board.of(width), 0, 0);
    }

    private BoardSet solve(Board board, int row, int column) {
        if (row == board.width()) {
            if (rule.isSatisfiedBy(board)) {
                return BoardSet.of(board);
            } else {
                return BoardSet.empty();
            }
        } else {
            int nextRow = row;
            int nextColumn = column + 1;
            if (nextColumn == board.width()) {
                nextColumn = 0;
                nextRow++;
            }
            BoardSet unoccupied = solve(board, nextRow, nextColumn);
            BoardSet occupied = solve(board.occupy(row, column), nextRow, nextColumn);
            return unoccupied.plus(occupied);
        }
    }
}
