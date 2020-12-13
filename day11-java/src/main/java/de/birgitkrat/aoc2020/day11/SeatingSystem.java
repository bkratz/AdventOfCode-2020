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

        if ((row - 1) >= 0) {
            if ((col - 1) >= 0) {
                result.add(seatIndex - (cols + 1));
            }

            result.add(seatIndex - cols);

            if ((col + 1) < cols) {
                result.add(seatIndex - (cols - 1));
            }
        }

        if ((col - 1) >= 0) {
            result.add(seatIndex - 1);
        }
        if ((col + 1) < cols) {
            result.add(seatIndex + 1);
        }

        if ((row + 1) < rows) {
            if ((col - 1) >= 0) {
                result.add(seatIndex + (cols - 1));
            }

            result.add(seatIndex + cols);

            if ((col + 1) < cols) {
                result.add(seatIndex + (cols + 1));
            }
        }

        return result;
    }

    List<List<Integer>> findAdjacentSeatIndicesPart2(final int seatIndex, final int rows, final int cols) {
        int row = seatIndex / cols;
        int col = seatIndex - (row * cols);

        List<List<Integer>> result = new ArrayList<>();

        // waagerecht
        List<Integer> subResult = new ArrayList<>();
        for (int i = col - 1; i >= 0; i--) {
            subResult.add(row * cols + i);
        }
        result.add(subResult);
        subResult = new ArrayList<>();
        for (int i = col + 1; i < cols; i++) {
            subResult.add(row * cols + i);
        }
        result.add(subResult);
        subResult = new ArrayList<>();

        // senkrecht
        for (int i = row - 1; i >= 0; i--) {
            subResult.add(i * cols + col);
        }
        result.add(subResult);
        subResult = new ArrayList<>();
        for (int i = row + 1; i < rows; i++) {
            subResult.add(i * cols + col);
        }
        result.add(subResult);
        subResult = new ArrayList<>();

        // from seatIndex to upper left
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            subResult.add(i * cols + j);
        }
        result.add(subResult);
        subResult = new ArrayList<>();

        // from seatIndex to lower right
        for (int i = row + 1, j = col + 1; i < rows && j < cols; i++, j++) {
            subResult.add(i * cols + j);
        }
        result.add(subResult);
        subResult = new ArrayList<>();

        // from seatIndex to upper right
        for (int i = row - 1, j = col + 1; i >= 0 && j < cols; i--, j++) {
            subResult.add(i * cols + j);
        }
        result.add(subResult);
        subResult = new ArrayList<>();

        // from seatIndex to lower left
        for (int i = row + 1, j = col - 1; i < rows && j >= 0; i++, j--) {
            subResult.add(i * cols + j);
        }
        result.add(subResult);

        return result;
    }
}
