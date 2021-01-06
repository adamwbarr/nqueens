package co.abarr.nqueens.rule;

import co.abarr.nqueens.Board;

/**
 * Created by adam on 06/01/2021.
 */
class StraightLine implements Rule {
    @Override
    public boolean isSatisfiedBy(Board board) {
        for (int i = 0; i < board.squares(); i++) {
            Board.Square square = board.square(i);
            if (square.isOccupied() && isLineFrom(square)) {
                return true;
            }
        }
        return false;
    }

    private boolean isLineFrom(Board.Square square0) {
        Board board = square0.board();
        for (int i1 = square0.index() + 1; i1 < board.squares(); i1++) {
            Board.Square square1 = board.square(i1);
            if (square1.isOccupied()) {
                int dr = square1.row() - square0.row();
                int dc = square1.column() - square0.column();
                int gcd = gcd(dr, Math.abs(dc));
                dr /= gcd;
                dc /= gcd;
                int r2 = square1.row() + dr;
                int c2 = square1.column() + dc;
                while (r2 < board.width() && c2 >= 0 && c2 < board.width()){
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
