package co.abarr.nqueens.rule;

import co.abarr.nqueens.Board;

import java.util.Arrays;

/**
 * A functional interface for defining composable rules.
 * <p>
 * Created by adam on 06/01/2021.
 */
public interface Rule {
    /**
     * Whether this rule is satisfied by a particular board.
     */
    boolean isSatisfiedBy(Board board);

    /**
     * Negates this rule.
     */
    default Rule negated() {
        return new Negate(this);
    }

    /**
     * A rule that is always satisfied.
     */
    Rule FALSE = board -> false;

    /**
     * A rule that is never satisfied.
     */
    Rule TRUE = board -> true;

    /**
     * A rule stating there is a horizontal conflict.
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
     * A rule stating there is a vertical conflict.
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
     * A rule stating there is a left diagonal conflict.
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
     * A rule stating there is a right diagonal conflict.
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
     * A rule stating that no queen may attack another.
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
    Rule NO_CONFLICTS = union(
        HORIZONTAL.negated(),
        VERTICAL.negated(),
        DIAGONAL_LEFT.negated(),
        DIAGONAL_RIGHT.negated()
    );

    /**
     * A rule stating the full n-queens property.
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
    Rule N_QUEENS = union(board -> board.occupied() == board.width(), NO_CONFLICTS);

    /**
     * A rule stating there are 3 queens in a line, at any angle.
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
