package co.abarr.nqueens.rule;

import co.abarr.nqueens.Board;

/**
 * Created by adam on 06/01/2021.
 */
class DiagonalRight implements Rule {
    @Override
    public boolean isSatisfiedBy(Board board) {
        int size = board.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board.isOccupied(i, j)) {
                    for (int offset = 1; offset < size - i; offset++) {
                        int iOffset = i + offset;
                        int jOffset = j + offset;
                        if (jOffset >= size) {
                            break;
                        } else if (board.isOccupied(iOffset, jOffset)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
