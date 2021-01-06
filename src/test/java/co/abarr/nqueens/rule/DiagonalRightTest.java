package co.abarr.nqueens.rule;

import co.abarr.nqueens.Board;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by adam on 06/01/2021.
 */
class DiagonalRightTest {
    DiagonalRight rule = new DiagonalRight();

    @Test
    void isSatisfiedBy_BoardWithConflictAtLeftEdge_ShouldBeTrue() {
        Board board = Board.fromString(
            "x...\n" +
            ".x..\n" +
            "....\n" +
            "...."
        );
        assertThat(rule.isSatisfiedBy(board)).isTrue();
    }

    @Test
    void isSatisfiedBy_BoardWithConflictAtRightEdge_ShouldBeTrue() {
        Board board = Board.fromString(
            "..x.\n" +
            "...x\n" +
            "....\n" +
            "...."
        );
        assertThat(rule.isSatisfiedBy(board)).isTrue();
    }

    @Test
    void isSatisfiedBy_BoardWithConflictAtBottomEdge_ShouldBeTrue() {
        Board board = Board.fromString(
            "....\n" +
            "....\n" +
            "x...\n" +
            ".x.."
        );
        assertThat(rule.isSatisfiedBy(board)).isTrue();
    }

    @Test
    void isSatisfiedBy_BoardWithConflictAtOppositeEnd_ShouldBeTrue() {
        Board board = Board.fromString(
            "x...\n" +
            "....\n" +
            "....\n" +
            "...x"
        );
        assertThat(rule.isSatisfiedBy(board)).isTrue();
    }

    @Test
    void isSatisfiedBy_BoardWithNoConflict_ShouldBeFalse() {
        Board board = Board.fromString(
            "x...\n" +
            "....\n" +
            "....\n" +
            "x..."
        );
        assertThat(rule.isSatisfiedBy(board)).isFalse();
    }
}