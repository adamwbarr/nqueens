package co.abarr.nqueens.rule;

import co.abarr.nqueens.Board;

import java.util.Arrays;

/**
 * A functional interface for defining composable rules.
 * <p>
 * This is the public API of the {@link co.abarr.nqueens.rule} package;
 * everything else is package-protected.
 * <p>
 * Created by adam on 06/01/2021.
 */
public interface Rule {
    /**
     * Finds the number of conflicts on the supplied board.
     */
    int breachesOn(Board board);

    /**
     * A rule breached if there is a horizontal conflict.
     * <p>
     * For example:
     * <pre>
     *     x.x
     *     ...
     *     ...
     * </pre>
     */
    Rule HORIZONTAL = new Horizontal();

    /**
     * A rule breached if there is a vertical conflict.
     * <p>
     * For example:
     * <pre>
     *     x..
     *     ...
     *     x..
     * </pre>
     */
    Rule VERTICAL = new Vertical();

    /**
     * A rule breached if there is a left diagonal conflict.
     * <p>
     * For example:
     * <pre>
     *     ..x
     *     ...
     *     x..
     * </pre>
     */
    Rule DIAGONAL_LEFT = new DiagonalLeft();

    /**
     * A rule breached if there is a right diagonal conflict.
     * <p>
     * For example:
     * <pre>
     *     x..
     *     ...
     *     ..x
     * </pre>
     */
    Rule DIAGONAL_RIGHT = new DiagonalRight();

    /**
     * The union (logical AND) of multiple rules.
     */
    static Rule union(Rule... rules) {
        return new Union(Arrays.asList(rules));
    }

    /**
     * A rule breached if any queen may attack another.
     * <p>
     * For an n*n board all queens must be arranged in such a way that no queen
     * is horizontally, vertically or diagonally in line with any other. This
     * is effectively the n-queens rule but without requiring there be any
     * specific number of queens on the board.
     * <p>
     * For example:
     * <pre>
     *      .x..     or     .x..     or     ....
     *      ...x            ...x            ....
     *      ....            x...            ....
     *      ....            ..x.            ...x
     * </pre>
     */
    Rule ATTACKS = union(
        HORIZONTAL,
        VERTICAL,
        DIAGONAL_LEFT,
        DIAGONAL_RIGHT
    );

    /**
     * A rule breached if the full n-queens property is not true.
     * <p>
     * For an n*n board there must be exactly n queens, arranged in such a way
     * that no queen can attack any other (either horizontally, vertically or
     * diagonally).
     * <p>
     * For example:
     * <pre>
     *      .x..
     *      ...x
     *      x...
     *      ..x.
     * </pre>
     */
    Rule N_QUEENS = union(board -> board.occupied() == board.width() ? 0 : 1, ATTACKS);

    /**
     * A rule breached if there are 3 queens in a line, at any angle.
     * <p>
     * For example:
     * <pre>
     *     x....     or     .......
     *     .....            .......
     *     .x...            ......x
     *     .....            ...x...
     *     ..x..            x......
     *                      .......
     *                      .......
     * </pre>
     */
    Rule STRAIGHT_LINES = new StraightLine();
}
