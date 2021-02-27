package co.abarr.nqueens.rule;

import co.abarr.nqueens.Board;

/**
 * Created by adam on 06/01/2021.
 */
class StraightLine implements Rule {
    @Override
    public int breachesOn(Board board) {
        int breaches = 0;
        for (Board.Square square : board) {
            if (square.isOccupied() && isLineFrom(square)) {
                breaches++;
            }
        }
        return breaches;
    }

    private boolean isLineFrom(Board.Square square0) {
        Board board = square0.board();
        for (int i = square0.index() + 1; i < board.squares(); i++) {
            Board.Square square1 = board.square(i);
            if (square1.isOccupied()) {
                int dr = square1.row() - square0.row();
                int dc = square1.column() - square0.column();
                int gcd = gcd(dr, Math.abs(dc));
                dr /= gcd;
                dc /= gcd;
                int r2 = square1.row() + dr;
                int c2 = square1.column() + dc;
                while (r2 < board.width() && c2 >= 0 && c2 < board.width()) {
                    if (board.isOccupied(r2, c2)) {
                        return true;
                    }
                    r2 += dr;
                    c2 += dc;
                }
            }
        }
        return false;
    }

    private static int gcd(int x, int y) {
        if (y == 0) {
            return x;
        } else {
            return gcd(y, x % y);
        }
    }
}
