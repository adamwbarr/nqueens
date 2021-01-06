package co.abarr.nqueens.co.abarr.nqueens.solver;

import co.abarr.nqueens.BoardSet;
import co.abarr.nqueens.rule.Rule;

/**
 * API for defining logic for solving n-queens style problems.
 * <p>
 * Created by adam on 06/01/2021.
 */
public interface Solver {
    /**
     * Finds all boards of given size solving the problem.
     */
    BoardSet solveFor(int size);

    /**
     * A solver that uses brute force to find solutions to an arbitrary rule.
     */
    static Solver bruteForce(Rule rule) {
        return new BruteForce(rule);
    }
}
