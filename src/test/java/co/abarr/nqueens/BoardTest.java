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
        String toString = Board.of(3).put(0, 1).put(2, 2).toString();
        assertThat(toString).isEqualTo(".x.\n...\n..x");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 8, 10})
    void put_InvalidRow_ShouldThrowException(int row) {
        Board board = Board.of(8);
        assertThatThrownBy(() -> board.put(row, 0)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 8, 10})
    void put_InvalidColumn_ShouldThrowException(int column) {
        Board board = Board.of(8);
        assertThatThrownBy(() -> board.put(0, column)).isInstanceOf(IllegalArgumentException.class);
    }

}