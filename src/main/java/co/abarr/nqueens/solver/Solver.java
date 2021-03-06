package co.abarr.nqueens.solver;

import co.abarr.nqueens.Board;
import co.abarr.nqueens.rule.Rule;

/**
 * API for defining logic for solving n-queens style problems.
 * <p>
 * This is the public API of the {@link co.abarr.nqueens.solver} package;
 * everything else is package-protected.
 * <p>
 * Created by adam on 06/01/2021.
 */
public interface Solver {
    /**
     * Finds a boards of given width that solves the problem.
     */
    Board solveFor(int width);

    /**
     * Logs a simple summary of what happened.
     */
    default Solver logging() {
        return new Logging(this);
    }

    /**
     * A solver that uses brute force to find solutions to an arbitrary rule.
     * <p>
     * Note - this is extremely slow for boards wider than 5 or 6.
     */
    static Solver bruteForce(Rule rule) {
        return new BruteForce(rule);
    }

    /**
     * A solver for the n-queens problem.
     * <p>
     * This is substantially faster than brute force - on my laptop it takes
     * <1s for boards of width 10 or less.
     */
    Solver N_QUEENS = Backtracking.N_QUEENS;

    /**
     * A solver for the extended n-queens problem.
     * <p>
     * This is defined as the n-queens problem, with the additional constraint
     * that no three queens are in a straight line at any angle.
     */
    Solver N_QUEENS_EXTENDED = Backtracking.N_QUEENS_EXTENDED;
}
