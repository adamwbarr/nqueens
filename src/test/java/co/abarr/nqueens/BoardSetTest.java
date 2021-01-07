package co.abarr.nqueens;

import co.abarr.nqueens.rule.Rule;
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

    @Test
    void plus_WithEmptySet_ShouldReturnSelf() {
        BoardSet set1 = BoardSet.of(Board.of(8));
        BoardSet set2 = BoardSet.empty();
        assertThat(set1.plus(set2)).isEqualTo(set1);
    }

    @Test
    void plus_OnEmptySet_ShouldReturnParameter() {
        BoardSet set1 = BoardSet.empty();
        BoardSet set2 = BoardSet.of(Board.of(8));
        assertThat(set1.plus(set2)).isEqualTo(set2);
    }

    @Test
    void plus_NonEmptySets_ShouldReturnCombinedSet() {
        Board board1 = Board.of(8).occupy(0, 0);
        Board board2 = Board.of(8).occupy(1, 1);
        BoardSet set1 = BoardSet.of(board1);
        BoardSet set2 = BoardSet.of(board2);
        assertThat(set1.plus(set2)).containsExactly(board1, board2);
    }

    @Test
    void satisfying_SimpleRule_ShouldReturnMatchingBoardsOnly() {
        BoardSet set = BoardSet.of(Board.of(8), Board.of(9), Board.of(10));
        Rule rule = board -> board.width() > 8;
        assertThat(set.filter(rule)).containsExactly(Board.of(9), Board.of(10));
    }
}