package co.abarr.nqueens.rule;

import co.abarr.nqueens.Board;

/**
 * Created by adam on 06/01/2021.
 */
class Horizontal implements Rule {
    @Override
    public boolean isSatisfiedBy(Board board) {
        for (Board.Square square : board) {
            if (square.isOccupied()) {
                int row = square.row();
                for (int column = square.column() + 1; column < board.width(); column++) {
                    if (board.isOccupied(row, column)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
