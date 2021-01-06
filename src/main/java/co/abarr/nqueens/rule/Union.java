package co.abarr.nqueens.rule;

import co.abarr.nqueens.Board;

import java.util.Objects;

/**
 * Created by adam on 06/01/2021.
 */
class Union implements Rule {
    private final Iterable<Rule> rules;

    public Union(Iterable<Rule> rules) {
        this.rules = Objects.requireNonNull(rules);
    }

    @Override
    public boolean isSatisfiedBy(Board board) {
        for (Rule rule : rules) {
            if (!rule.isSatisfiedBy(board)) {
                return false;
            }
        }
        return true;
    }
}
