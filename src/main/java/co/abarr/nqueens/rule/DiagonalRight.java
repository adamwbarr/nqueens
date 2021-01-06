package co.abarr.nqueens.rule;

import co.abarr.nqueens.Board;

/**
 * Created by adam on 06/01/2021.
 */
class DiagonalRight implements Rule {
    @Override
    public boolean isSatisfiedBy(Board board) {
        int width = board.width();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if (board.isOccupied(i, j)) {
                    for (int offset = 1; offset < width - i; offset++) {
                        int iOffset = i + offset;
                        int jOffset = j + offset;
                        if (jOffset >= width) {
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
