package de.birgitkrat.aoc2020.day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SeatingSystem {

    private final Path path =
            Paths.get(Objects.requireNonNull(this.getClass().getClassLoader().getResource("input.txt")).getFile());

    long processPart1() throws IOException {
        final List<String> lines = Files.readAllLines(path);
        return processInputPart1(lines);
    }

    long processPart2() throws IOException {
        final List<String> lines = Files.readAllLines(path);
        return processInputPart2(lines);
    }

    long processInputPart1(final List<String> input) {
        int rows = input.size();
        int cols = input.get(0).length();

        String nextGeneration = String.join("", input);
        String previousGeneration = null;

        while (!nextGeneration.equals(previousGeneration)) {
            previousGeneration = nextGeneration;
            nextGeneration = calculateNextGenerationPart1(previousGeneration, rows, cols);
        }

        return nextGeneration.chars().filter(value -> value == '#').count();
    }

    long processInputPart2(final List<String> input) {
        int rows = input.size();
        int cols = input.get(0).length();

        String nextGeneration = String.join("", input);
        String previousGeneration = null;

        while (!nextGeneration.equals(previousGeneration)) {
            previousGeneration = nextGeneration;
            nextGeneration = calculateNextGenerationPart2(previousGeneration, rows, cols);
        }

        return nextGeneration.chars().filter(value -> value == '#').count();
    }

    String calculateNextGenerationPart1(final String input, int rows, int cols) {
        return IntStream.range(0, (rows * cols))
                .parallel()
                .mapToObj(seatIndex -> calculateNextSeatGenerationPart1(input, seatIndex, rows, cols))
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    String calculateNextGenerationPart2(final String input, int rows, int cols) {
        return IntStream.range(0, (rows * cols))
                .parallel()
                .mapToObj(seatIndex -> calculateNextSeatGenerationPart2(input, seatIndex, rows, cols))
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    char calculateNextSeatGenerationPart1(final String input, final int seatIndex, final int rows,
            final int cols) {
        final char[] chars = input.toCharArray();
        final char seatChar = chars[seatIndex];
        if (seatChar == '.') {
            return seatChar;
        }

        long countAdjacentOccupied = countAdjacentOccupiedSeatsPart1(seatIndex, rows, cols, chars);
        if (seatChar == 'L' && countAdjacentOccupied == 0) {
            return '#';
        }
        if (seatChar == '#' && countAdjacentOccupied >= 4) {
            return 'L';
        }
        return seatChar;
    }

    char calculateNextSeatGenerationPart2(final String input, final int seatIndex, final int rows,
            final int cols) {
        final char[] chars = input.toCharArray();
        final char seatChar = chars[seatIndex];
        if (seatChar == '.') {
            return seatChar;
        }

        int countAdjacentOccupied = countAdjacentOccupiedSeatsPart2(seatIndex, rows, cols, chars);
        if (seatChar == 'L' && countAdjacentOccupied == 0) {
            return '#';
        }
        if (seatChar == '#' && countAdjacentOccupied >= 5) {
            return 'L';
        }
        return seatChar;
    }

    private long countAdjacentOccupiedSeatsPart1(final int seatIndex, final int rows, final int cols, final char[] chars) {
        final List<Integer> adjacentSeatIndices = findAdjacentSeatIndicesPart1(seatIndex, rows, cols);

        return adjacentSeatIndices.stream().filter(i -> chars[i] == '#').count();
    }

    private int countAdjacentOccupiedSeatsPart2(final int seatIndex, final int rows, final int cols, final char[] chars) {
        final List<List<Integer>> adjacentSeatIndices = findAdjacentSeatIndicesPart2(seatIndex, rows, cols);

        int countAdjacentOccupied = 0;
        for (final List<Integer> adjacentSeatIndex : adjacentSeatIndices) {
            for (final int index : adjacentSeatIndex) {
                if (chars[index] != '.') {
                    if (chars[index] == '#') {
                        ++countAdjacentOccupied;
                    }
                    // only first occurence of "L" or '#' ist important
                    break;
                }
            }
        }
        return countAdjacentOccupied;
    }

    List<Integer> findAdjacentSeatIndicesPart1(final int seatIndex, final int rows, final int cols) {
        List<Integer> result = new ArrayList<>();

        int row = seatIndex / cols;
        int col = seatIndex - (row * cols);

        int iMin = Integer.max(row - 1, 0);
        int iMax = Integer.min(row + 1, rows-1);

        int kMin = Integer.max(col - 1, 0);
        int kMax = Integer.min(col + 1, cols-1);

        for (int i = iMin; i<= iMax; i++) {
           for (int k = kMin; k<=kMax; k++) {
               result.add(toSeatIndex(i, k, cols));
           }
        }
        return result.stream().filter(si -> si != seatIndex).collect(Collectors.toList());
    }

    private Integer toSeatIndex(final int i, final int k, final int cols) {
        return i * cols + k;
    }

    List<List<Integer>> findAdjacentSeatIndicesPart2(final int seatIndex, final int rows, final int cols) {
        int row = seatIndex / cols;
        int col = seatIndex - (row * cols);

        int rounds = IntStream.of(row, col, rows-row, cols-col).max().getAsInt();

        List<Integer> horizontalLeft = new ArrayList<>();
        List<Integer> horizontalRight = new ArrayList<>();
        List<Integer> verticalUp = new ArrayList<>();
        List<Integer> verticalDown = new ArrayList<>();
        List<Integer> diagonalLeftUp = new ArrayList<>();
        List<Integer> diagonalRightUp = new ArrayList<>();
        List<Integer> diagonalLeftDown = new ArrayList<>();
        List<Integer> diagonalRightDown = new ArrayList<>();

        for (int i=1; i<=rounds; i++) {
            if (col-i >=0) {
                horizontalLeft.add(row * cols + (col-i));
            }
            if (col+i < cols) {
                horizontalRight.add(row * cols + (col+i));
            }
            if (row-i >=0) {
                verticalUp.add((row-i) * cols + col);
            }
            if (row+i < rows) {
                verticalDown.add((row+i) * cols + col);
            }
            if (col-i >= 0 && row-i >=0) {
                diagonalLeftUp.add((row - i) * cols + (col - i));
            }
            if (row+i < rows && col+i < cols) {
                diagonalRightDown.add((row +i) * cols + (col+i));
            }
            if (row-i >=0 && col+i < cols) {
                diagonalRightUp.add((row-i) * cols + (col+i));
            }
            if (row+i < rows && col-i >=0) {
                diagonalLeftDown.add((row+i) * cols + (col-i));
            }
        }
        return List.of(horizontalLeft, horizontalRight, verticalUp, verticalDown, diagonalLeftUp, diagonalRightDown, diagonalRightUp, diagonalLeftDown);
    }
}
