package co.abarr.nqueens;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Created by adam on 06/01/2021.
 */
class BoardSetTest {
    @Test
    void of_NullBoard_ShouldThrowException() {
        assertThatThrownBy(() -> BoardSet.of(Board.of(1), null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    void size_OfEmptySet_ShouldBeZero() {
        assertThat(BoardSet.empty()).hasSize(0);
    }

    @Test
    void size_OfNonEmptySet_ShouldBeCorrect() {
        BoardSet set = BoardSet.of(
            Board.of(8).occupy(4, 4),
            Board.of(8).occupy(4, 5)
        );
        assertThat(set).hasSize(2);
    }
}