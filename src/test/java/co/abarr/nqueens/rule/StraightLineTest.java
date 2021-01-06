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
    void isSatisifiedBy_NoQueens_ShouldBeFalse() {
        Board board = Board.of(8);
        assertThat(rule.isSatisfiedBy(board)).isFalse();
    }

    @Test
    void isSatisifiedBy_OnlyTwoQueens_ShouldBeFalse() {
        Board board = Board.fromString(
            "...x\n" +
            "....\n" +
            "....\n" +
            "...x"
        );
        assertThat(rule.isSatisfiedBy(board)).isFalse();
    }

    @Test
    void isSatisifiedBy_HorizontalLine_ShouldBeTrue() {
        Board board = Board.fromString(
            "xx.x\n" +
            "....\n" +
            "....\n" +
            "...."
        );
        assertThat(rule.isSatisfiedBy(board)).isTrue();
    }

    @Test
    void isSatisifiedBy_VerticalLine_ShouldBeTrue() {
        Board board = Board.fromString(
            "...x\n" +
            "...x\n" +
            "....\n" +
            "...x"
        );
        assertThat(rule.isSatisfiedBy(board)).isTrue();
    }

    @Test
    void isSatisifiedBy_DiagonalLine_ShouldBeTrue() {
        Board board = Board.fromString(
            "x...\n" +
            ".x..\n" +
            "....\n" +
            "...x"
        );
        assertThat(rule.isSatisfiedBy(board)).isTrue();
    }

    @Test
    void isSatisifiedBy_DiagonalLineAndLargerGapFirst_ShouldBeTrue() {
        Board board = Board.fromString(
            "x...\n" +
            "....\n" +
            "..x.\n" +
            "...x"
        );
        assertThat(rule.isSatisfiedBy(board)).isTrue();
    }

    @Test
    void isSatisifiedBy_TwoDownTwoRightLine_ShouldBeTrue() {
        Board board = Board.fromString(
            "x....\n" +
            ".....\n" +
            "..x..\n" +
            ".....\n" +
            "....x"
        );
        assertThat(rule.isSatisfiedBy(board)).isTrue();
    }

    @Test
    void isSatisifiedBy_TwoDownTwoLeftLine_ShouldBeTrue() {
        Board board = Board.fromString(
            "....x\n" +
            ".....\n" +
            "..x..\n" +
            ".....\n" +
            "x...."
        );
        assertThat(rule.isSatisfiedBy(board)).isTrue();
    }

    @Test
    void isSatisifiedBy_OneDownThreeRightLine_ShouldBeTrue() {
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
        assertThat(rule.isSatisfiedBy(board)).isTrue();
    }

    @Test
    void isSatisifiedBy_ThreeDownOneLeftLine_ShouldBeTrue() {
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
        assertThat(rule.isSatisfiedBy(board)).isTrue();
    }

    @Test
    void isSatisifiedBy_OneDownThreeLeftLine_ShouldBeTrue() {
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
        assertThat(rule.isSatisfiedBy(board)).isTrue();
    }
}