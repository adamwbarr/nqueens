package co.abarr.nqueens.solver;

import co.abarr.nqueens.BoardSet;
import co.abarr.nqueens.rule.Rule;

/**
 * API for defining logic for solving n-queens style problems.
 * <p>
 * Created by adam on 06/01/2021.
 */
public interface Solver {
    /**
     * Finds all boards of given width that solve the problem.
     */
    BoardSet solveFor(int width);

    /**
     * Wraps this solver in one that logs a simple summary of what happened.
     */
    default Solver logging() {
        return new Logging(this);
    }

    /**
     * Filters the solution down to only those satisfying an additional rule.
     */
    default Solver satisfying(Rule rule) {
        return width -> solveFor(width).satisfying(rule);
    }

    /**
     * A solver that uses brute force to find solutions to an arbitrary rule.
     * <p>
     * This is extremely slow for boards wider than 5 or 6.
     */
    static Solver bruteForce(Rule rule) {
        return new BruteForce(rule);
    }

    /**
     * A faster solver specifically for the N-queens problem.
     * <p>
     * On my laptop this takes under 1s for boards of width 10 or less.
     */
    Solver N_QUEENS = new Backtracking();

    /**
     * N-queens solutions with the additional constraint of no straight lines.
     */
    Solver N_QUEENS_PLUS = N_QUEENS.satisfying(Rule.STRAIGHT_LINE.negate());
}
