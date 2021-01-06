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
    private final int width;

    private Board(BigInteger squares, int width) {
        this.squares = squares;
        this.width = width;
    }

    /**
     * The width of the board - the number of squares per side.
     * <p>
     * In total the board will contain <code>width * width</code> squares.
     */
    public int width() {
        return width;
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
        return new Board(squares.setBit(bitIndex(row, column)), width);
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
        return width == board.width && squares.equals(board.squares);
    }

    @Override
    public int hashCode() {
        return Objects.hash(squares, width);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int row = 0; row < width; row++) {
            for (int column = 0; column < width; column++) {
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
        return row * width + column;
    }

    private void checkValid(int row, int column) {
        checkValid(row);
        checkValid(column);
    }

    private void checkValid(int index) {
        if (index < 0 || index >= width) {
            throw new IllegalArgumentException(String.format("Invalid index %d for board of width %d", index, width));
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
        int width = s.substring(0, s.indexOf('\n')).length();
        Board board = Board.of(width);
        int row = 0;
        int column = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (column == width) {
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
        if (row != width - 1) {
            throw new IllegalArgumentException("Insufficient rows in \"" + s + "\"");
        }
        return board;
    }

    /**
     * Creates a new (empty) board of the supplied width.
     * <p>
     * The width is the number of squares per side, meaning the resulting board
     * will contain <code>width * width</code> empty squares.
     * <p>
     * An exception will be thrown if <code>size</code> is not a positive value.
     */
    public static Board of(int width) {
        if (width <= 0) {
            throw new IllegalArgumentException("Invalid width: " + width);
        } else {
            return new Board(BigInteger.ZERO, width);
        }
    }
}