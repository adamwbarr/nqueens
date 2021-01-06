package co.abarr.nqueens.rule;

import co.abarr.nqueens.Board;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by adam on 06/01/2021.
 */
public class NQueensTest {
    Rule rule = Rule.N_QUEENS;

    @Test
    void isSatisfiedBy_EmptyBoard_ShouldBeFalse() {
        Board board = Board.of(8);
        assertThat(rule.isSatisfiedBy(board)).isFalse();
    }

    @Test
    void isSatisfiedBy_BoardWithInsufficientQueens_ShouldBeFalse() {
        Board board = Board.fromString(
            ".x..\n" +
            "....\n" +
            "x...\n" +
            "..x."
        );
        assertThat(rule.isSatisfiedBy(board)).isFalse();
    }

    @Test
    void isSatisfiedBy_BoardWithNoConflicts_ShouldBeTrue() {
        Board board = Board.fromString(
            ".x..\n" +
            "...x\n" +
            "x...\n" +
            "..x."
        );
        assertThat(rule.isSatisfiedBy(board)).isTrue();
    }
}
