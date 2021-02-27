package co.abarr.nqueens.rule;

import co.abarr.nqueens.Board;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by adam on 06/01/2021.
 */
class RuleTest {
    @Test
    void attacks_OnEmptyBoard_ShouldBeZero() {
        Board board = Board.of(8);
        assertThat(Rule.ATTACKS.breachesOn(board)).isEqualTo(0);
    }

    @Test
    void attacks_OnBoardWithFewerThanNQueens_ShouldBeZero() {
        Board board = Board.fromString(
            ".x..\n" +
            "....\n" +
            "x...\n" +
            "..x."
        );
        assertThat(Rule.ATTACKS.breachesOn(board)).isEqualTo(0);
    }

    @Test
    void attacks_OnBoardWithNQueens_ShouldBeZero() {
        Board board = Board.fromString(
            ".x..\n" +
            "...x\n" +
            "x...\n" +
            "..x."
        );
        assertThat(Rule.ATTACKS.breachesOn(board)).isEqualTo(0);
    }

    @Test
    void attacks_OnBoardWithMultipleConflicts_ShouldBeCorrect() {
        Board board = Board.fromString(
            ".x.x\n" +
            "....\n" +
            "..x.\n" +
            ".x.."
        );
        assertThat(Rule.ATTACKS.breachesOn(board)).isEqualTo(3);
    }

    @Test
    void nQueens_OnEmptyBoard_ShouldBeOne() {
        Board board = Board.of(8);
        assertThat(Rule.N_QUEENS.breachesOn(board)).isEqualTo(1);
    }

    @Test
    void nQueens_BoardWithInsufficientQueens_ShouldBeOne() {
        Board board = Board.fromString(
            ".x..\n" +
            "....\n" +
            "x...\n" +
            "..x."
        );
        assertThat(Rule.N_QUEENS.breachesOn(board)).isEqualTo(1);
    }

    @Test
    void nQueens_BoardWithNoConflicts_ShouldBeZero() {
        Board board = Board.fromString(
            ".x..\n" +
            "...x\n" +
            "x...\n" +
            "..x."
        );
        assertThat(Rule.N_QUEENS.breachesOn(board)).isEqualTo(0);
    }
}
