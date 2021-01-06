package co.abarr.nqueens.rule;

import co.abarr.nqueens.Board;

/**
 * Created by adam on 06/01/2021.
 */
class StraightLine implements Rule {
    @Override
    public boolean isSatisfiedBy(Board board) {
        for (int i = 0; i < board.width(); i++) {
            for (int j = 0; j < board.width(); j++) {
                if (board.isOccupied(i, j)) {
                    if (isLineFrom(board, i, j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isLineFrom(Board board, int i0, int j0) {
        int i1 = i0;
        int j1 = j0 + 1;
        if (j1 == board.width()) {
            j1 = 0;
            i1++;
        }
        for (; i1 < board.width(); i1++) {
            for (; j1 < board.width(); j1++) {
                if (board.isOccupied(i1, j1)) {
                    int di = i1 - i0;
                    int dj = j1 - j0;
                    int gcd = gcd(di, dj);
                    di /= gcd;
                    dj /= gcd;
                    int i2 = i1 + di;
                    int j2 = j1 + dj;
                    while (i2 < board.width() && j2 < board.width()) {
                        if (board.isOccupied(i2, j2)) {
                            return true;
                        }
                        i2 += di;
                        j2 += dj;
                    }
                }
            }
        }
        return false;
    }

    private static int gcd(int x, int y) {
        if (x == 0) {
            return y;
        } else {
            return gcd(y, x % y);
        }
    }
}
