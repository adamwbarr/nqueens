package co.abarr.nqueens;

import java.math.BigInteger;
import java.util.Objects;

/**
 * An immutable square board.
 * <p>
 * A board may or may not have a single queen on every square.
 * <p>
 * Created by adam on 05/01/2021.
 */
public class Board {
    private final BigInteger squares;
    private final int size;

    private Board(BigInteger squares, int size) {
        this.squares = squares;
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return size == board.size && squares.equals(board.squares);
    }

    @Override
    public int hashCode() {
        return Objects.hash(squares, size);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                if (isSet(row, column)) {
                    builder.append('x');
                } else {
                    builder.append('.');
                }
            }
            builder.append("\n");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    private boolean isSet(int row, int column) {
        return squares.testBit(row * size + column);
    }

    /**
     * Creates a new empty board of the supplied size.
     * <p>
     * The size is the number of squares per side, meaning the resulting board
     * will contain <code>size * size</code> empty squares.
     * <p>
     * An exception will be thrown if <code>size</code> is not a positive value.
     */
    public static Board of(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size is not a positive value: " + size);
        } else {
            return new Board(BigInteger.ZERO, size);
        }
    }
}
