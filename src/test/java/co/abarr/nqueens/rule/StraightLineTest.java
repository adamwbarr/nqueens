package co.abarr.nqueens.rule;

import co.abarr.nqueens.Board;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by adam on 06/01/2021.
 */
class StraightLineTest {
    Rule rule = new StraightLine();

    @Test
    void breachesOn_NoQueens_ShouldBeZero() {
        Board board = Board.of(8);
        assertThat(rule.breachesOn(board)).isEqualTo(0);
    }

    @Test
    void breachesOn_OnlyTwoQueens_ShouldBeZero() {
        Board board = Board.fromString(
            "...x\n" +
            "....\n" +
            "....\n" +
            "...x"
        );
        assertThat(rule.breachesOn(board)).isEqualTo(0);
    }

    @Test
    void breachesOn_HorizontalLine_ShouldBeOne() {
        Board board = Board.fromString(
            "xx.x\n" +
            "....\n" +
            "....\n" +
            "...."
        );
        assertThat(rule.breachesOn(board)).isEqualTo(1);
    }

    @Test
    void breachesOn_VerticalLine_ShouldBeOne() {
        Board board = Board.fromString(
            "...x\n" +
            "...x\n" +
            "....\n" +
            "...x"
        );
        assertThat(rule.breachesOn(board)).isEqualTo(1);
    }

    @Test
    void breachesOn_DiagonalLine_ShouldBeOne() {
        Board board = Board.fromString(
            "x...\n" +
            ".x..\n" +
            "....\n" +
            "...x"
        );
        assertThat(rule.breachesOn(board)).isEqualTo(1);
    }

    @Test
    void breachesOn_DiagonalLineAndLargerGapFirst_ShouldBeOne() {
        Board board = Board.fromString(
            "x...\n" +
            "....\n" +
            "..x.\n" +
            "...x"
        );
        assertThat(rule.breachesOn(board)).isEqualTo(1);
    }

    @Test
    void breachesOn_TwoDownTwoRightLine_ShouldBeOne() {
        Board board = Board.fromString(
            "x....\n" +
            ".....\n" +
            "..x..\n" +
            ".....\n" +
            "....x"
        );
        assertThat(rule.breachesOn(board)).isEqualTo(1);
    }

    @Test
    void breachesOn_TwoDownTwoLeftLine_ShouldBeOne() {
        Board board = Board.fromString(
            "....x\n" +
            ".....\n" +
            "..x..\n" +
            ".....\n" +
            "x...."
        );
        assertThat(rule.breachesOn(board)).isEqualTo(1);
    }

    @Test
    void breachesOn_OneDownThreeRightLine_ShouldBeOne() {
        Board board = Board.fromString(
            "x.......\n" +
            "........\n" +
            "........\n" +
            ".x......\n" +
            "........\n" +
            "........\n" +
            "..x.....\n" +
            "........"
        );
        assertThat(rule.breachesOn(board)).isEqualTo(1);
    }

    @Test
    void breachesOn_ThreeDownOneLeftLine_ShouldBeOne() {
        Board board = Board.fromString(
            "..x.....\n" +
            "........\n" +
            "........\n" +
            ".x......\n" +
            "........\n" +
            "........\n" +
            "x.......\n" +
            "........"
        );
        assertThat(rule.breachesOn(board)).isEqualTo(1);
    }

    @Test
    void breachesOn_OneDownThreeLeftLine_ShouldBeOne() {
        Board board = Board.fromString(
            ".......x\n" +
            ".....x..\n" +
            "...x....\n" +
            "........\n" +
            "........\n" +
            "........\n" +
            "........\n" +
            "........"
        );
        assertThat(rule.breachesOn(board)).isEqualTo(1);
    }
}