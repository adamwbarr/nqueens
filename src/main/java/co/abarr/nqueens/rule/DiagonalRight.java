package co.abarr.nqueens.rule;

import co.abarr.nqueens.Board;

/**
 * Created by adam on 06/01/2021.
 */
class DiagonalRight implements Rule {
    @Override
    public int breachesOn(Board board) {
        int breaches = 0;
        for (Board.Square square : board) {
            if (square.isOccupied()) {
                int limit = board.width() - Math.max(square.row(), square.column());
                for (int offset = 1; offset < limit; offset++) {
                    int row = square.row() + offset;
                    int column = square.column() + offset;
                    if (board.isOccupied(row, column)) {
                        breaches++;
                    }
                }
            }
        }
        return breaches;
    }
}
