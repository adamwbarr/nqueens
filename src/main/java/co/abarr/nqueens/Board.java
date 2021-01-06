package co.abarr.nqueens;

import java.math.BigInteger;
import java.util.Objects;

/**
 * An immutable square chessboard.
 * <p>
 * A board may or may not have a single queen on each square.
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

    /**
     * The size of the board - the number of squares per side.
     * <p>
     * In total the board will contain <code>size * size</code> squares.
     */
    public int size() {
        return size;
    }

    /**
     * Whether a square on the board is occupied by a queen.
     * <p>
     * An exception will be thrown if a row or column index is out of bounds.
     */
    public boolean isOccupied(int row, int column) {
        return squares.testBit(bitIndex(row, column));
    }

    /**
     * Occupies the square at the supplied position with a queen.
     * <p>
     * An exception will be thrown if a row or column index is out of bounds,
     * or there if there already is a queen occupying that square.
     */
    public Board occupy(int row, int column) {
        return new Board(squares.setBit(bitIndex(row, column)), size);
    }

    /**
     * The number of occupied squares on the board.
     */
    public int occupied() {
        return squares.bitCount();
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
                if (isOccupied(row, column)) {
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

    private int bitIndex(int row, int column) {
        checkValid(row, column);
        return row * size + column;
    }

    private void checkValid(int row, int column) {
        checkValid(row);
        checkValid(column);
    }

    private void checkValid(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException(String.format("Invalid index %d for board of size %d", index, size));
        }
    }

    /**
     * Constructs a board from a string.
     * <p>
     * A valid string is one as produced by the {@link #toString()} method,
     * with a line per row, within which each square is represented by a single
     * character: either 'x' if that square is occupied, or '.' otherwise.
     * <p>
     * For example:
     * <pre>
     *     .x..
     *     ..x.
     *     x...
     *     ...x
     * </pre>
     */
    public static Board fromString(String s) {
        int size = s.substring(0, s.indexOf('\n')).length();
        Board board = Board.of(size);
        int row = 0;
        int column = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (column == size) {
                if (c != '\n') {
                    throw new IllegalArgumentException(String.format("Unexpected character at index %d in \"%s\"", i, s));
                } else {
                    row++;
                    column = 0;
                }
            } else {
                if (c == 'x') {
                    board = board.occupy(row, column);
                } else if (c != '.') {
                    throw new IllegalArgumentException(String.format("Unexpected character at index %d in \"%s\"", i, s));
                }
                column++;
            }
        }
        if (row != size - 1) {
            throw new IllegalArgumentException("Insufficient rows");
        }
        return board;
    }

    /**
     * Creates a new (empty) board of the supplied size.
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