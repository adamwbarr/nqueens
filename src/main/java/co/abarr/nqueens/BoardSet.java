package co.abarr.nqueens;

import co.abarr.nqueens.rule.Rule;

import java.util.*;

/**
 * An immutable set of boards.
 * <p>
 * Guaranteed not to contain null.
 * <p>
 * Created by adam on 06/01/2021.
 */
public class BoardSet extends AbstractSet<Board> {
    private final Set<Board> boards;

    private BoardSet(Set<Board> boards) {
        this.boards = boards;
    }

    /**
     * An iterator over the boards in the set, in no particular order.
     */
    @Override
    public Iterator<Board> iterator() {
        return Collections.unmodifiableCollection(boards).iterator();
    }

    /**
     * The number of boards in the set.
     */
    @Override
    public int size() {
        return boards.size();
    }

    /**
     * Filters down to only those boards that satisfy the supplied rule.
     * <p>
     * This is an immutable operation - it returns a new object and does not
     * modify this one.
     */
    public BoardSet satisfying(Rule rule) {
        Set<Board> satisfying = new HashSet<>(size());
        for (Board board : boards) {
            if (rule.isSatisfiedBy(board)) {
                satisfying.add(board);
            }
        }
        return new BoardSet(satisfying);
    }

    /**
     * Adds a board to the set.
     * <p>
     * This is an immutable operation - it returns a new object and does not
     * modify this one.
     */
    public BoardSet plus(Board board) {
        return plus(Collections.singleton(board));
    }

    /**
     * Adds boards to the set.
     * <p>
     * This is an immutable operation - it returns a new object and does not
     * modify this one.
     */
    public BoardSet plus(Board... boards) {
        return plus(Arrays.asList(boards));
    }

    /**
     * Adds boards to the set.
     * <p>
     * This is an immutable operation - it returns a new object and does not
     * modify this one.
     */
    public BoardSet plus(Iterable<? extends Board> boards) {
        if (this.boards.isEmpty()) {
            return BoardSet.of(boards);
        } else {
            Iterator<? extends Board> iterator = boards.iterator();
            if (iterator.hasNext()) {
                Set<Board> merged = new HashSet<>(this.boards);
                while (iterator.hasNext()) {
                    merged.add(iterator.next());
                }
                return new BoardSet(merged);
            } else {
                return this;
            }
        }
    }

    /**
     * This method is not supported.
     *
     * @deprecated Use immutable operation {{@link #plus(Board)}} instead.
     */
    @Override
    @Deprecated
    public boolean add(Board board) {
        return super.add(board);
    }

    /**
     * This method is not supported.
     *
     * @deprecated Use immutable operation {{@link #plus(Iterable)}} instead.
     */
    @Override
    @Deprecated
    public boolean addAll(Collection<? extends Board> c) {
        return super.addAll(c);
    }

    /**
     * A set containing no boards.
     */
    public static BoardSet empty() {
        return EMPTY;
    }

    private static final BoardSet EMPTY = new BoardSet(Collections.emptySet());

    /**
     * A set containing the supplied boards.
     */
    public static BoardSet of(Board... boards) {
        return of(Arrays.asList(boards));
    }

    /**
     * A set containing the supplied boards.
     */
    public static BoardSet of(Iterable<? extends Board> boards) {
        Set<Board> copy = new HashSet<>();
        for (Board board : boards) {
            copy.add(Objects.requireNonNull(board));
        }
        return new BoardSet(copy);
    }
}
