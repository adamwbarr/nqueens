package co.abarr.nqueens.rule;

import co.abarr.nqueens.Board;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by adam on 06/01/2021.
 */
class VerticalTest {
    Vertical rule = new Vertical();

    @Test
    void isSatisfiedBy_BoardWithConflict_ShouldBeTrue() {
        Board board = Board.fromString(
            "x...\n" +
            "....\n" +
            "....\n" +
            "x..."
        );
        assertThat(rule.isSatisfiedBy(board)).isTrue();
    }

    @Test
    void isSatisfiedBy_BoardWithNoConflict_ShouldBeFalse() {
        Board board = Board.fromString(
            "x...\n" +
            "..x.\n" +
            "....\n" +
            "...."
        );
        assertThat(rule.isSatisfiedBy(board)).isFalse();
    }
}