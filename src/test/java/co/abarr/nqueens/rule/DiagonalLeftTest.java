package co.abarr.nqueens.rule;

import co.abarr.nqueens.Board;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by adam on 06/01/2021.
 */
class DiagonalLeftTest {
    DiagonalLeft rule = new DiagonalLeft();

    @Test
    void breachesOn_BoardWithConflictAtRightEdge_ShouldBeCorrect() {
        Board board = Board.fromString(
            "...x\n" +
            "..x.\n" +
            "....\n" +
            "...."
        );
        assertThat(rule.breachesOn(board)).isEqualTo(1);
    }

    @Test
    void breachesOn_BoardWithConflictAtLeftEdge_ShouldBeOne() {
        Board board = Board.fromString(
            ".x..\n" +
            "x...\n" +
            "....\n" +
            "...."
        );
        assertThat(rule.breachesOn(board)).isEqualTo(1);
    }

    @Test
    void breachesOn_BoardWithConflictAtBottomEdge_ShouldBeOne() {
        Board board = Board.fromString(
            "....\n" +
            "....\n" +
            ".x..\n" +
            "x..."
        );
        assertThat(rule.breachesOn(board)).isEqualTo(1);
    }

    @Test
    void breachesOn_BoardWithConflictAtOppositeEnd_ShouldBeOne() {
        Board board = Board.fromString(
            "...x\n" +
            "....\n" +
            "....\n" +
            "x..."
        );
        assertThat(rule.breachesOn(board)).isEqualTo(1);
    }

    @Test
    void breachesOn_BoardWithSingleQueenOnBottomEdge_ShouldBeZero() {
        Board board = Board.fromString(
            "....\n" +
            "....\n" +
            "....\n" +
            ".x.."
        );
        assertThat(rule.breachesOn(board)).isEqualTo(0);
    }

    @Test
    void breachesOn_BoardWithNoConflict_ShouldBeZero() {
        Board board = Board.fromString(
            "x...\n" +
            "....\n" +
            "....\n" +
            "x..."
        );
        assertThat(rule.breachesOn(board)).isEqualTo(0);
    }
}