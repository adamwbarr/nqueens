package co.abarr.nqueens.solver;

import co.abarr.nqueens.BoardSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * Created by adam on 06/01/2021.
 */
class Logging implements Solver {
    private static final Logger logger = LoggerFactory.getLogger(Logging.class);
    private final Solver delegate;

    public Logging(Solver delegate) {
        this.delegate = Objects.requireNonNull(delegate);
    }

    @Override
    public BoardSet solveFor(int width) {
        long t0 = System.currentTimeMillis();
        BoardSet solutions = delegate.solveFor(width);
        logger.info(
            "Took {}ms to find {} solutions for board of width {} with {}",
            System.currentTimeMillis() - t0,
            solutions.size(),
            width,
            delegate
        );
        return solutions;
    }
}
