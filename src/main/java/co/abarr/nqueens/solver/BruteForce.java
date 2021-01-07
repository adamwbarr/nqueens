package co.abarr.nqueens.solver;

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
        return solve(Board.of(width), 0);
    }

    private BoardSet solve(Board board, int index) {
        if (index == board.squares()) {
            if (rule.isSatisfiedBy(board)) {
                return BoardSet.of(board);
            } else {
                return BoardSet.empty();
            }
        } else {
            BoardSet unoccupied = solve(board, index + 1);
            BoardSet occupied = solve(board.occupy(index), index + 1);
            return unoccupied.plus(occupied);
        }
    }
}
