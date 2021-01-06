package co.abarr.nqueens.rule;

import co.abarr.nqueens.Board;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by adam on 06/01/2021.
 */
class RuleTest {
    @Test
    void noConflicts_OnEmptyBoard_ShouldBeTrue() {
        Board board = Board.of(8);
        assertThat(Rule.NO_CONFLICTS.isSatisfiedBy(board)).isTrue();
    }

    @Test
    void noConflicts_OnBoardWithFewerThanNQueens_ShouldBeTrue() {
        Board board = Board.fromString(
            ".x..\n" +
            "....\n" +
            "x...\n" +
            "..x."
        );
        assertThat(Rule.NO_CONFLICTS.isSatisfiedBy(board)).isTrue();
    }

    @Test
    void noConflicts_OnBoardWithNQueens_ShouldBeTrue() {
        Board board = Board.fromString(
            ".x..\n" +
            "...x\n" +
            "x...\n" +
            "..x."
        );
        assertThat(Rule.NO_CONFLICTS.isSatisfiedBy(board)).isTrue();
    }

    @Test
    void noConflicts_OnBoardWithconflicts_ShouldBeFalse() {
        Board board = Board.fromString(
            ".x..\n" +
            "....\n" +
            "....\n" +
            ".x.."
        );
        assertThat(Rule.NO_CONFLICTS.isSatisfiedBy(board)).isFalse();
    }

    @Test
    void nQueens_OnEmptyBoard_ShouldBeFalse() {
        Board board = Board.of(8);
        assertThat(Rule.N_QUEENS.isSatisfiedBy(board)).isFalse();
    }

    @Test
    void nQueens_BoardWithInsufficientQueens_ShouldBeFalse() {
        Board board = Board.fromString(
            ".x..\n" +
            "....\n" +
            "x...\n" +
            "..x."
        );
        assertThat(Rule.N_QUEENS.isSatisfiedBy(board)).isFalse();
    }

    @Test
    void nQueens_BoardWithNoConflicts_ShouldBeTrue() {
        Board board = Board.fromString(
            ".x..\n" +
            "...x\n" +
            "x...\n" +
            "..x."
        );
        assertThat(Rule.N_QUEENS.isSatisfiedBy(board)).isTrue();
    }
}
