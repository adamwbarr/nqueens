package co.abarr.nqueens.rule;

import co.abarr.nqueens.Board;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Created by adam on 06/01/2021.
 */
class NegateTest {
    @Test
    void constructor_OnNullDelegate_ShouldThrowException() {
        assertThatThrownBy(() -> new Negate(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    void isSatisfiedBy_OnTrueFromDelegate_ShouldBeFalse() {
        Negate rule = new Negate(Rule.TRUE);
        Board board = Board.of(8);
        assertThat(rule.isSatisfiedBy(board)).isFalse();
    }

    @Test
    void isSatisfiedBy_OnFalseFromDelegate_ShouldBeTrue() {
        Negate rule = new Negate(Rule.FALSE);
        Board board = Board.of(8);
        assertThat(rule.isSatisfiedBy(board)).isTrue();
    }
}