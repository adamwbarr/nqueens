package co.abarr.nqueens.solver;

import co.abarr.nqueens.Board;
import co.abarr.nqueens.rule.Rule;

import java.util.Objects;
import java.util.Optional;

/**
 * The solution described here: https://www.geeksforgeeks.org/n-queen-problem-backtracking-3.
 * <p>
 * Created by adam on 06/01/2021.
 */
class Backtracking implements Solver {
    private final Rule rule;

    public Backtracking(Rule rule) {
        this.rule = Objects.requireNonNull(rule);
    }

    @Override
    public Board solveFor(int width) {
        return solutionFor(Board.of(width), 0).orElseThrow();
    }

    private Optional<Board> solutionFor(Board board, int column) {
        if (column == board.width()) {
            return Optional.of(board);
        } else {
            for (int row = 0; row < board.width(); row++) {
                Board next = board.occupy(row, column);
                if (rule.isSatisfiedBy(next)) {
                    Optional<Board> solution = solutionFor(next, column + 1);
                    if (solution.isPresent()) {
                        return solution;
                    }
                }
            }
            return Optional.empty();
        }
    }

    /**
     * A solver for the n-queens problem.
     * <p>
     * This is substantially faster than brute force - on my laptop it takes
     * <1s for boards of width 10 or less.
     */
    public static final Backtracking N_QUEENS = new Backtracking(Rule.NO_CONFLICTS);


    /**
     * A solver for the extended n-queens problem.
     * <p>
     * This is defined as the n-queens problem, with the additional constraint
     * that no three queens are in a straight line at any angle.
     */
    public static final Backtracking N_QUEENS_EXTENDED = new Backtracking(
        Rule.union(Rule.NO_CONFLICTS, Rule.STRAIGHT_LINES.negated())
    );
}
