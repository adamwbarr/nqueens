package co.abarr.nqueens;

import co.abarr.nqueens.solver.Solver;

/**
 * Created by adam on 06/01/2021.
 */
public class Main {
    public static void main(String[] args) {
        int width = widthFrom(args);
        System.out.printf("Solving for %dx%<d board...\n", width);
        long t0 = System.currentTimeMillis();
        BoardSet solutions = Solver.N_QUEENS_EXTENDED.solveFor(width);
        long d = System.currentTimeMillis() - t0;
        String separator = "-".repeat(width);
        System.out.println(separator);
        for (Board solution : solutions) {
            System.out.println(solution);
            System.out.println(separator);
        }
        System.out.printf("Found %d solutions in %dms\n", solutions.size(), d);
    }

    private static int widthFrom(String[] args) {
        if (args.length == 0) {
            return 8;
        } else {
            return Integer.parseInt(args[0]);
        }
    }
}
