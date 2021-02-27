package co.abarr.nqueens.rule;

import co.abarr.nqueens.Board;

/**
 * Created by adam on 06/01/2021.
 */
class Vertical implements Rule {
    @Override
    public int breachesOn(Board board) {
        int breaches = 0;
        for (Board.Square square : board) {
            if (square.isOccupied()) {
                int column = square.column();
                for (int row = square.row() + 1; row < board.width(); row++) {
                    if (board.isOccupied(row, column)) {
                        breaches++;
                    }
                }
            }
        }
        return breaches;
    }
}
