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
    public int breachesOn(Board board) {
        int breaches = 0;
        for (Rule rule : rules) {
            breaches += rule.breachesOn(board);
        }
        return breaches;
    }
}
