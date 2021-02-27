package co.abarr.nqueens.solver;

import co.abarr.nqueens.Board;
import co.abarr.nqueens.rule.Rule;

import java.util.Objects;
import java.util.Optional;

/**
 * Created by adam on 06/01/2021.
 */
class BruteForce implements Solver {
    private final Rule rule;

    public BruteForce(Rule rule) {
        this.rule = Objects.requireNonNull(rule);
    }

    @Override
    public Board solveFor(int width) {
        return solve(Board.of(width), 0).orElseThrow();
    }

    private Optional<Board> solve(Board board, int index) {
        if (index == board.squares()) {
            if (rule.isSatisfiedBy(board)) {
                return Optional.of(board);
            } else {
                return Optional.empty();
            }
        } else {
            Optional<Board> unoccupied = solve(board, index + 1);
            if (unoccupied.isPresent()) {
                return unoccupied;
            } else {
                return solve(board.occupy(index), index + 1);
            }
        }
    }
}
