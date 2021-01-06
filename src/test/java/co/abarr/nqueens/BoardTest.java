package co.abarr.nqueens;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Created by adam on 05/01/2021.
 */
class BoardTest {
    @ParameterizedTest
    @ValueSource(ints = {0, -1, -213})
    void of_InvalidSize_ShouldThrowException(int size) {
        assertThatThrownBy(() -> Board.of(size)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void toString_ForEmptyBoardOfSize2_ShouldBeCorrect() {
        String toString = Board.of(2).toString();
        assertThat(toString).isEqualTo("..\n..");
    }

    @Test
    void toString_ForEmptyBoardOfSize3_ShouldBeCorrect() {
        String toString = Board.of(3).toString();
        assertThat(toString).isEqualTo("...\n...\n...");
    }

    @Test
    void toString_ForBoardWithMultipleQueens_ShouldBeCorrect() {
        String toString = Board.of(3).occupy(0, 1).occupy(2, 2).toString();
        assertThat(toString).isEqualTo(".x.\n...\n..x");
    }

    @Test
    void fromString_OfEmptyBoard_ShouldBeParsedCorrectly() {
        Board board = Board.fromString(
            "...\n" +
            "...\n" +
            "..."
        );
        assertThat(board).isEqualTo(Board.of(3));
    }

    @Test
    void fromString_OfBoardWithSingleQueen_ShouldBeParsedCorrectly() {
        Board board = Board.fromString(
            "...\n" +
            ".x.\n" +
            "..."
        );
        assertThat(board).isEqualTo(Board.of(3).occupy(1, 1));
    }

    @Test
    void fromString_OfBoardWithMultipleQueens_ShouldBeParsedCorrectly() {
        Board board = Board.fromString(
            "..x\n" +
            ".x.\n" +
            "..."
        );
        assertThat(board).isEqualTo(Board.of(3).occupy(0, 2).occupy(1, 1));
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "",
        "...\n...",
        "...\n..",
        ".?.\n...\n..."
    })
    void fromString_OfInvalidString_ShouldThrowException(String invalid) {
        assertThatThrownBy(() -> Board.fromString(invalid));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 8, 10})
    void occupy_InvalidRow_ShouldThrowException(int row) {
        Board board = Board.of(8);
        assertThatThrownBy(() -> board.occupy(row, 0)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 8, 10})
    void occupy_InvalidColumn_ShouldThrowException(int column) {
        Board board = Board.of(8);
        assertThatThrownBy(() -> board.occupy(0, column)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void isConflicting_OfEmptyBoard_ShouldBeFalse() {
        Board board = Board.of(8);
        assertThat(board.isConflicting()).isFalse();
    }

    @Test
    void isConflicting_OfHorizontalConflict_ShouldBeTrue() {
        Board board = Board.fromString(
            "x..x\n" +
            "....\n" +
            "....\n" +
            "...."
        );
        assertThat(board.isConflicting()).isTrue();
    }

    @Test
    void isConflicting_OfVerticalConflict_ShouldBeTrue() {
        Board board = Board.fromString(
            "...x\n" +
            "....\n" +
            "....\n" +
            "...x"
        );
        assertThat(board.isConflicting()).isTrue();
    }

    @Test
    void isConflicting_OfDiagonalalLeftConflict_ShouldBeTrue() {
        Board board = Board.fromString(
            "x...\n" +
            "....\n" +
            "....\n" +
            "...x"
        );
        assertThat(board.isConflicting()).isTrue();
    }

    @Test
    void isConflicting_OfDiagonalalRightConflict_ShouldBeTrue() {
        Board board = Board.fromString(
            "...x\n" +
            "....\n" +
            "....\n" +
            "x..."
        );
        assertThat(board.isConflicting()).isTrue();
    }

    @Test
    void isConflicting_OfValidBoard_ShouldBeFalse() {
        Board board = Board.fromString(
            ".x..\n" +
            "...x\n" +
            "x...\n" +
            "..x."
        );
        assertThat(board.isConflicting()).isFalse();
    }
}