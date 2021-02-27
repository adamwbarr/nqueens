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
    void breachesOn_BoardWithConflict_ShouldBeOne() {
        Board board = Board.fromString(
            "x...\n" +
            "....\n" +
            "....\n" +
            "x..."
        );
        assertThat(rule.breachesOn(board)).isEqualTo(1);
    }

    @Test
    void breachesOn_BoardWithNoConflict_ShouldBeZero() {
        Board board = Board.fromString(
            "x...\n" +
            "..x.\n" +
            "....\n" +
            "...."
        );
        assertThat(rule.breachesOn(board)).isEqualTo(0);
    }
}