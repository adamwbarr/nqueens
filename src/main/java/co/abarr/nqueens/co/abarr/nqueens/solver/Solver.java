package co.abarr.nqueens.co.abarr.nqueens.solver;

import co.abarr.nqueens.BoardSet;

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
}
