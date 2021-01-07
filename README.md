# N-Queens

This is a Java implementation of a solver for
the [N-queens](https://en.wikipedia.org/wiki/Eight_queens_puzzle)
problem.

### Running It

The project is built using Gradle; to compile the code and run all unit tests type:

```shell
$ ./gradlew test
```

### Solving The Problem

The goal of the project is to solve the following problem:

> Place N queens on an NxN chess board so that none of them attack each other (the classic n-queens
> problem). Additionally, please make sure that no three queens are in a straight line at ANY angle,
> so queens on A1, C2 and E3, despite not attacking each other, form a straight line at some angle.

You can print all solutions to this problem for the `8x8` board like this:

```java
BoardSet solutions = Solver.N_QUEENS_EXTENDED.solveFor(8);
for (Board solution : solutions){
    System.out.println(solution);
    System.out.println("--------");
}
```

On a 3.1GHz MacBook Pro this takes ~100ms and finds 8 solutions. It starts to become very slow with
boards larger than `10x10` (`11x11` takes ~30s, `12x12` ~2m30s).

The code mainly prioritises clarity over speed, so these performance numbers could surely be 
improved somewhat; also many of the algorithms are parallelizable, which could offer further 
speedups.