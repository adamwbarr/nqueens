package co.abarr.nqueens;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Created by adam on 05/01/2021.
 */
class BoardTest {
    @Test
    void of_Zero_ShouldThrowException() {
        assertThatThrownBy(() -> Board.of(0)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void of_NegativeValue_ShouldThrowException() {
        assertThatThrownBy(() -> Board.of(-1)).isInstanceOf(IllegalArgumentException.class);
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
}