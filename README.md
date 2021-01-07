# N-Queens

This is a Java implementation of a solver for a version of the
[N-queens](https://en.wikipedia.org/wiki/Eight_queens_puzzle) problem.

The goal of the project is to solve the following problem:

> Place N queens on an NxN chess board so that none of them attack each other (the classic n-queens
> problem). Additionally, please make sure that no three queens are in a straight line at ANY angle,
> so queens on A1, C2 and E3, despite not attacking each other, form a straight line at some angle.

### Running It

The project is built using Gradle. To compile the code and execute all unit tests run:

```shell
$ ./gradlew test
```

To find all solutions for an `8x8` board, run:

```shell
$ ./gradlew run --args="8"
```

On a 3.1GHz MacBook Pro this takes ~80ms and finds 8 solutions. It starts to become very slow with
boards larger than `11x11` (`12x12` takes ~10s, `13x13` ~1m30s).

The code mainly prioritises clarity over speed, so these performance numbers could surely be
improved somewhat. Also, the search algorithm is parallelizable, which should offer further
speedups.