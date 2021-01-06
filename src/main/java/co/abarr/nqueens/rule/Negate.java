package co.abarr.nqueens.rule;

import co.abarr.nqueens.Board;

import java.util.Objects;

/**
 * Created by adam on 06/01/2021.
 */
class Negate implements Rule {
    private final Rule rule;

    public Negate(Rule rule) {
        this.rule = Objects.requireNonNull(rule);
    }

    @Override
    public boolean isSatisfiedBy(Board board) {
        return !rule.isSatisfiedBy(board);
    }
}
