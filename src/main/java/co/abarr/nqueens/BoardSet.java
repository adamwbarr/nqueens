package co.abarr.nqueens;

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
     * A set containing no boards.
     */
    public static BoardSet empty() {
        return of();
    }

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
