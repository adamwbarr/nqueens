package co.abarr.nqueens;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * An immutable square chessboard.
 * <p>
 * Squares on a board may be individually referenced either by row and column
 * or by (row-first) index. Each square may by occupied by a single queen.*
 * <p>
 * Created by adam on 05/01/2021.
 */
public class Board implements Iterable<Board.Square> {
    private static final char UNOCCUPIED = '.';
    private static final char OCCUPIED = 'x';
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
     * The total number of squares on the board.
     */
    public int squares() {
        return width * width;
    }

    /**
     * The number of occupied squares on the board.
     */
    public int occupied() {
        return squares.bitCount();
    }

    /**
     * An iterator over all squares on this board.
     * <p>
     * Squares are iterated in ascending index order.
     */
    @Override
    public Iterator<Square> iterator() {
        return new SquareIterator();
    }

    private class SquareIterator implements Iterator<Square> {
        private int next = 0;

        @Override
        public boolean hasNext() {
            return next < squares();
        }

        @Override
        public Square next() {
            if (hasNext()) {
                return square(next++);
            } else {
                throw new NoSuchElementException();
            }
        }
    }

    /**
     * The square at the supplied index.
     * <p>
     * Indices are row-major, so for a 3*3 board the index of each square is as
     * follows:
     * <pre>
     *     0 1 2
     *     3 4 5
     *     6 7 8
     * </pre>
     * <p>
     * An exception will be thrown if the supplied index is out of bounds.
     */
    public Square square(int index) {
        if (index < 0 || index >= squares()) {
            throw new IndexOutOfBoundsException(index);
        } else {
            return new Square(index);
        }
    }

    /**
     * The square at the supplied row and column.
     * <p>
     * An exception will be thrown if either index is out of bounds.
     */
    public Square square(int row, int column) {
        if (row < 0 || row >= width) {
            throw new IndexOutOfBoundsException("Row out of range: " + row);
        } else if (column < 0 || column >= width) {
            throw new IndexOutOfBoundsException("Column out of range: " + column);
        } else {
            return square(row * width + column);
        }
    }

    /**
     * Whether a square on the board is occupied by a queen.
     * <p>
     * An exception will be thrown if the index is out of bounds.
     */
    public boolean isOccupied(int index) {
        return square(index).isOccupied();
    }

    /**
     * Occupies the square at the supplied position with a queen.
     * <p>
     * An exception will be thrown the index is out of bounds.
     */
    public Board occupy(int index) {
        return square(index).occupy();
    }

    /**
     * Whether a square on the board is occupied by a queen.
     * <p>
     * An exception will be thrown if either index is out of bounds.
     */
    public boolean isOccupied(int row, int column) {
        return square(row, column).isOccupied();
    }

    /**
     * Occupies the square at the supplied position with a queen.
     * <p>
     * An exception will be thrown if either index is out of bounds.
     */
    public Board occupy(int row, int column) {
        return square(row, column).occupy();
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
                if (square(row, column).isOccupied()) {
                    builder.append(OCCUPIED);
                } else {
                    builder.append(UNOCCUPIED);
                }
            }
            builder.append("\n");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    /**
     * A square on this chessboard.
     */
    public class Square {
        private final int index;

        private Square(int index) {
            this.index = index;
        }

        /**
         * The unique index of this square.
         */
        public int index() {
            return index;
        }

        /**
         * The row index of this square.
         */
        public int row() {
            return index / width;
        }

        /**
         * The column index of this square.
         */
        public int column() {
            return index % width;
        }

        /**
         * The board that this square is on.
         */
        public Board board() {
            return Board.this;
        }

        /**
         * Whether this square is occupied by a queen.
         */
        public boolean isOccupied() {
            return squares.testBit(index);
        }

        /**
         * Occupies this square with a queen.
         * <p>
         * This is an immutable operation - it will return a new board with this
         * square occupied.
         */
        public Board occupy() {
            return new Board(squares.setBit(index), width);
        }

        @Override
        public String toString() {
            return String.format("(%d,%d)=[%s]", row(), column(), isOccupied() ? OCCUPIED : UNOCCUPIED);
        }
    }

    /**
     * Constructs a board from a string.
     * <p>
     * A valid string is one as produced by the {@link #toString()} method:
     * one line per row, within which each square is represented by a single
     * character; either 'x' if that square is occupied, or '.' otherwise.
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
                if (c == OCCUPIED) {
                    board = board.square(row, column).occupy();
                } else if (c != UNOCCUPIED) {
                    throw new IllegalArgumentException(String.format("Unexpected character at index %d in \"%s\"", i, s));
                }
                column++;
            }
        }
        if (row < width - 1) {
            throw new IllegalArgumentException("Insufficient rows in \"" + s + "\"");
        }
        return board;
    }

    /**
     * Creates a new (empty) board of the supplied width.
     * <p>
     * The width is the number of squares per side, meaning the resulting board
     * will contain <code>width * width</code> squares in total.
     * <p>
     * An exception will be thrown if the width is not a positive value.
     */
    public static Board of(int width) {
        if (width <= 0) {
            throw new IllegalArgumentException("Invalid width: " + width);
        } else {
            return new Board(BigInteger.ZERO, width);
        }
    }
}