package co.abarr.nqueens.rule;

import co.abarr.nqueens.Board;

/**
 * Created by adam on 06/01/2021.
 */
class DiagonalLeft implements Rule {
    @Override
    public boolean isSatisfiedBy(Board board) {
        for (Board.Square square : board) {
            if (square.isOccupied()) {
                int limit = Math.min(board.width() - square.row(), square.column() + 1);
                for (int offset = 1; offset < limit; offset++) {
                    int row = square.row() + offset;
                    int column = square.column() - offset;
                    if (board.isOccupied(row, column)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
