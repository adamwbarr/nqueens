package co.abarr.nqueens.main;

import co.abarr.nqueens.Board;
import co.abarr.nqueens.solver.Solver;

/**
 * Created by adam on 06/01/2021.
 */
public class Main {
    public static void main(String[] args) {
        int width = widthFrom(args);
        System.out.printf("Solving for %dx%<d board...\n", width);
        long t0 = System.currentTimeMillis();
        Solver solver;
        if (isOriginalFrom(args)) {
            solver = Solver.N_QUEENS;
        } else {
            solver = Solver.N_QUEENS_EXTENDED;
        }
        Board solution = solver.solveFor(width);
        long d = System.currentTimeMillis() - t0;
        System.out.println(solution);
        System.out.printf("Found solution in %dms\n", d);
    }

    private static int widthFrom(String[] args) {
        return Integer.parseInt(args[0]);
    }

    private static boolean isOriginalFrom(String[] args) {
        return args.length == 2 && args[1].equals("--original");
    }
}
