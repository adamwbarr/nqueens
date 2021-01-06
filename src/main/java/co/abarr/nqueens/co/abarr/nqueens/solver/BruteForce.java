package co.abarr.nqueens.co.abarr.nqueens.solver;

import co.abarr.nqueens.Board;
import co.abarr.nqueens.BoardSet;
import co.abarr.nqueens.rule.Rule;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by adam on 06/01/2021.
 */
class BruteForce implements Solver {
    private final Rule rule;

    public BruteForce(Rule rule) {
        this.rule = Objects.requireNonNull(rule);
    }

    @Override
    public BoardSet solveFor(int size) {
        Set<Board> solutions = new HashSet<>();
        Board board = Board.of(size);
        return null;
    }

    private BoardSet solveFrom(Board basedOn, int row, int column) {
        return null;
    }
}
