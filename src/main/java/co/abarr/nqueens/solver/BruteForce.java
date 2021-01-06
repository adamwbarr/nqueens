package co.abarr.nqueens.solver;

import co.abarr.nqueens.Board;
import co.abarr.nqueens.BoardSet;
import co.abarr.nqueens.rule.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by adam on 06/01/2021.
 */
class BruteForce implements Solver {
    private static final Logger logger = LoggerFactory.getLogger(BruteForce.class);
    private final Rule rule;

    public BruteForce(Rule rule) {
        this.rule = Objects.requireNonNull(rule);
    }

    @Override
    public BoardSet solveFor(int width) {
        Result result = new Result();
        solve(Board.of(width), 0, 0, result);
        result.logProgress();
        return BoardSet.of(result.solutions);
    }

    private void solve(Board board, int row, int column, Result result) {
        if (row == board.width()) {
            result.add(board);
        } else {
            int nextRow = row;
            int nextColumn = column + 1;
            if (nextColumn == board.width()) {
                nextColumn = 0;
                nextRow++;
            }
            solve(board, nextRow, nextColumn, result);
            solve(board.occupy(row, column), nextRow, nextColumn, result);
        }
    }

    private class Result {
        private final long t0 = System.currentTimeMillis();
        private final List<Board> solutions = new ArrayList<>();
        private long tested = 0;

        public void add(Board board) {
            tested++;
            if (rule.isSatisfiedBy(board)) {
                solutions.add(board);
            }
            if (tested % 100000 == 0) {
                logProgress();
            }
        }


        public void logProgress() {
            long duration = System.currentTimeMillis() - t0;
            logger.info(String.format(
                "Took %dms to test %d boards (%.3fus each), of which %d are solutions)",
                duration,
                tested,
                1000 * duration / (double)tested,
                solutions.size()
            ));
        }
    }
}
