package co.abarr.nqueens.rule;

import co.abarr.nqueens.Board;

import java.util.Arrays;

/**
 * Functional interface for defining pluggable rules.
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
    default Rule negate() {
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
     * A rule satisfied when there is a horizontal conflict on the board.
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
     * A rule satisfied when there is a vertical conflict on the board.
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
     * A rule satisfied when there is a left diagonal conflict on the board.
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
     * A rule satisfied when there is a left diagonal conflict on the board.
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
     * The union (AND) of the supplied rules.
     */
    static Rule union(Rule... rules) {
        return new Union(Arrays.asList(rules));
    }

    /**
     * A rule satisfied when no queen may attack another.
     * <p>
     * For an n*n board all queens must be arranged in such a way that no queen
     * can attack any other (either horizontally, vertically or diagonally).
     * <p>
     * For example:
     * <pre>
     *      .x..
     *      ...x
     *      ....
     *      ....
     * </pre>
     */
    Rule NO_CONFLICTS = union(
        HORIZONTAL.negate(),
        VERTICAL.negate(),
        DIAGONAL_LEFT.negate(),
        DIAGONAL_RIGHT.negate()
    );

    /**
     * A rule satisfied when the full n-queens property is true.
     * <p>
     * For an n*n board there must be n queens arranged in such a way that no
     * queen can attack any other (either horizontally, vertically or
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
     * A rule satisfied when there are 3 queens in a line at any angle.
     * <p>
     * "Queens on A1, C2 and E3, despite not attacking each other, form a straight
     * line at some angle". eg:
     * <pre>
     *     x....
     *     .....
     *     .x...
     *     .....
     *     ..x..
     * </pre>
     */
    Rule STRAIGHT_LINES = new StraightLine();
}
