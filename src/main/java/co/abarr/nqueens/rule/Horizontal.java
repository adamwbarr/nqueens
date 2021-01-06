package co.abarr.nqueens.rule;

import co.abarr.nqueens.Board;

/**
 * Created by adam on 06/01/2021.
 */
class Horizontal implements Rule {
    @Override
    public boolean isSatisfiedBy(Board board) {
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.size(); j++) {
                if (board.isOccupied(i, j)) {
                    for (int k = j + 1; k < board.size(); k++) {
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
