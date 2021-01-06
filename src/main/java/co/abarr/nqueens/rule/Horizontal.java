package co.abarr.nqueens.rule;

import co.abarr.nqueens.Board;

/**
 * Created by adam on 06/01/2021.
 */
class Horizontal implements Rule {
    @Override
    public boolean isSatisfiedBy(Board board) {
        for (int i = 0; i < board.width(); i++) {
            for (int j = 0; j < board.width(); j++) {
                if (board.isOccupied(i, j)) {
                    for (int k = j + 1; k < board.width(); k++) {
                        if (board.isOccupied(i, k)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
