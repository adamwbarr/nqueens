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
    void of_InvalidWidth_ShouldThrowException(int width) {
        assertThatThrownBy(() -> Board.of(width)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void toString_ForEmptyBoardOfWidth2_ShouldBeCorrect() {
        String toString = Board.of(2).toString();
        assertThat(toString).isEqualTo("..\n..");
    }

    @Test
    void toString_ForEmptyBoardOfWidth3_ShouldBeCorrect() {
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

    @ParameterizedTest
    @ValueSource(ints = {-1, 8, 10})
    void isOccupied_OfInvalidRow_ShouldThrowException(int row) {
        Board board = Board.of(8).occupy(4, 5);
        assertThatThrownBy(() -> board.isOccupied(row, 0)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 8, 10})
    void isOccupied_OfInvalidColumn_ShouldThrowException(int column) {
        Board board = Board.of(8).occupy(4, 5);
        assertThatThrownBy(() -> board.isOccupied(0, column)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void isOccupied_OfUnoccupiedSquare_ShouldBeFalse() {
        Board board = Board.of(8).occupy(4, 5);
        assertThat(board.isOccupied(0, 0)).isFalse();
    }

    @Test
    void isOccupied_OfOccupiedSquare_ShouldBeTrue() {
        Board board = Board.of(8).occupy(4, 5);
        assertThat(board.isOccupied(4, 5)).isTrue();
    }

    @Test
    void width_OfSimpleBoard_ShouldBeCorrect() {
        Board board = Board.of(8);
        assertThat(board.width()).isEqualTo(8);
    }

    @Test
    void occupied_OfEmptyBoard_ShouldBeZero() {
        Board board = Board.of(8);
        assertThat(board.occupied()).isEqualTo(0);
    }

    @Test
    void occupied_OfBoardWithSingleQueen_ShouldBeOne() {
        Board board = Board.of(8).occupy(4, 4);
        assertThat(board.occupied()).isEqualTo(1);
    }

    @Test
    void occupied_OfBoardWithMultipleQueens_ShouldBeCorrect() {
        Board board = Board.fromString(
            "..x.\n" +
            ".x..\n" +
            "..x.\n" +
            "x..x"
        );
        assertThat(board.occupied()).isEqualTo(5);
    }
}