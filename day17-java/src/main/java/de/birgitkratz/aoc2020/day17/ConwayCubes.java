package de.birgitkratz.aoc2020.day17;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class ConwayCubes {

    private final Path path =
            Paths.get(Objects.requireNonNull(this.getClass().getClassLoader().getResource("input.txt")).getFile());

    public int processPart1() throws IOException {
        final List<String> lines = Files.readAllLines(path);

        int[][] input = new int[lines.size()][lines.get(0).length()];

        for (int i=0; i<lines.size(); i++) {
            final char[] chars = lines.get(i).toCharArray();
            for (int j=0; j<chars.length; j++) {
                if (chars[j] == '#') {
                    input[i][j] = 1;
                }
            }
        }
        return processInputPart1(input);
    }

    public int processInputPart1(final int[][] input) {
        final List<int[][]> ints = cycleNTimes(input, 6);

        return ints.stream().mapToInt(i -> Arrays.stream(i).flatMapToInt(Arrays::stream).sum()).sum();
    }

    List<int[][]> cycleNTimes(int[][] input, int times) {
        int[][] cycleLayer0 = input;
        int[][] cycleLayer1 = new int[input.length][input[0].length];

        int[][] nextCycleLayer0 = cycle(cycleLayer0, cycleLayer1, cycleLayer1);
        int[][] nextCycleLayer1 = cycle(cycleLayer1, new int[cycleLayer0.length][cycleLayer0[0].length], cycleLayer0);

        cycleLayer0 = nextCycleLayer0;
        cycleLayer1 = nextCycleLayer1;
        int[][] cycleLayer2 = new int[cycleLayer0.length][cycleLayer0[0].length];

        nextCycleLayer0 = cycle(cycleLayer0, cycleLayer1, cycleLayer1);
        nextCycleLayer1 = cycle(cycleLayer1, cycleLayer2, cycleLayer0);
        int[][] nextCycleLayer2 = cycle(cycleLayer2, new int[cycleLayer0.length][cycleLayer0[0].length], cycleLayer1);

        cycleLayer0 = nextCycleLayer0;
        cycleLayer1 = nextCycleLayer1;
        cycleLayer2 = nextCycleLayer2;
        int[][] cycleLayer3 = new int[cycleLayer0.length][cycleLayer0[0].length];

        nextCycleLayer0 = cycle(cycleLayer0, cycleLayer1, cycleLayer1);
        nextCycleLayer1 = cycle(cycleLayer1, cycleLayer2, cycleLayer0);
        nextCycleLayer2 = cycle(cycleLayer2, cycleLayer3, cycleLayer1);
        int[][] nextCycleLayer3 = cycle(cycleLayer3, new int[cycleLayer0.length][cycleLayer0[0].length], cycleLayer2);

        cycleLayer0 = nextCycleLayer0;
        cycleLayer1 = nextCycleLayer1;
        cycleLayer2 = nextCycleLayer2;
        cycleLayer3 = nextCycleLayer3;
        int[][] cycleLayer4 = new int[cycleLayer0.length][cycleLayer0[0].length];

        nextCycleLayer0 = cycle(cycleLayer0, cycleLayer1, cycleLayer1);
        nextCycleLayer1 = cycle(cycleLayer1, cycleLayer2, cycleLayer0);
        nextCycleLayer2 = cycle(cycleLayer2, cycleLayer3, cycleLayer1);
        nextCycleLayer3 = cycle(cycleLayer3, cycleLayer4, cycleLayer2);
        int[][] nextCycleLayer4 = cycle(cycleLayer4, new int[cycleLayer0.length][cycleLayer0[0].length], cycleLayer3);

        cycleLayer0 = nextCycleLayer0;
        cycleLayer1 = nextCycleLayer1;
        cycleLayer2 = nextCycleLayer2;
        cycleLayer3 = nextCycleLayer3;
        cycleLayer4 = nextCycleLayer4;
        int[][] cycleLayer5 = new int[cycleLayer0.length][cycleLayer0[0].length];

        nextCycleLayer0 = cycle(cycleLayer0, cycleLayer1, cycleLayer1);
        nextCycleLayer1 = cycle(cycleLayer1, cycleLayer2, cycleLayer0);
        nextCycleLayer2 = cycle(cycleLayer2, cycleLayer3, cycleLayer1);
        nextCycleLayer3 = cycle(cycleLayer3, cycleLayer4, cycleLayer2);
        nextCycleLayer4 = cycle(cycleLayer4, cycleLayer5, cycleLayer3);
        int[][] nextCycleLayer5 = cycle(cycleLayer5, new int[cycleLayer0.length][cycleLayer0[0].length], cycleLayer4);

        cycleLayer0 = nextCycleLayer0;
        cycleLayer1 = nextCycleLayer1;
        cycleLayer2 = nextCycleLayer2;
        cycleLayer3 = nextCycleLayer3;
        cycleLayer4 = nextCycleLayer4;
        cycleLayer5 = nextCycleLayer5;
        int[][] cycleLayer6 = new int[cycleLayer0.length][cycleLayer0[0].length];

        nextCycleLayer0 = cycle(cycleLayer0, cycleLayer1, cycleLayer1);
        nextCycleLayer1 = cycle(cycleLayer1, cycleLayer2, cycleLayer0);
        nextCycleLayer2 = cycle(cycleLayer2, cycleLayer3, cycleLayer1);
        nextCycleLayer3 = cycle(cycleLayer3, cycleLayer4, cycleLayer2);
        nextCycleLayer4 = cycle(cycleLayer4, cycleLayer5, cycleLayer3);
        nextCycleLayer5 = cycle(cycleLayer5, cycleLayer6, cycleLayer4);
        int[][] nextCycleLayer6 = cycle(cycleLayer6, new int[cycleLayer0.length][cycleLayer0[0].length], cycleLayer5);

        return List.of(nextCycleLayer0, nextCycleLayer1, nextCycleLayer2, nextCycleLayer3, nextCycleLayer4, nextCycleLayer5,
                nextCycleLayer6, nextCycleLayer6, nextCycleLayer5, nextCycleLayer4, nextCycleLayer3, nextCycleLayer2,
                nextCycleLayer1);
    }

    public int[][] surroundWithZeroRowsAndColumns(final int[][] input) {
        int[][] result = new int[input.length + 2][input[0].length + 2];
        IntStream.range(0, result[0].length).forEach(i -> result[0][i] = 0);
        for (int r = 0; r < input.length; r++) {
            result[r + 1][0] = 0;
            System.arraycopy(input[r], 0, result[r + 1], 1, input[0].length);
            result[r + 1][result[0].length - 1] = 0;
        }

        return result;
    }


    int[][] cycle(final int[][] layer0, final int[][] layer1, final int[][] layer_1) {
        final int[][] neighbourCounts = countNeighboursMatrix(layer0, layer1, layer_1);
        final int[][] sLayer0 = surroundWithZeroRowsAndColumns(layer0);

        int[][] result = new int[neighbourCounts.length][neighbourCounts[0].length];
        for (int r = 0; r < neighbourCounts.length; r++) {
            for (int c = 0; c < neighbourCounts[0].length; c++) {
                switch (neighbourCounts[r][c]) {
                case 2:
                    if (sLayer0[r][c] == 1) {
                        result[r][c] = 1;
                    }
                    break;
                case 3:
                    result[r][c] = 1;
                    break;
                default:
                    result[r][c] = 0;
                }
            }
        }
        return result;
    }

    public int[][] countNeighboursMatrix(final int[][] layer0, final int[][] layer1, final int[][] layer_1) {
        final int[][] sLayer0 = surroundWithZeroRowsAndColumns(layer0);
        final int[][] sLayer1 = surroundWithZeroRowsAndColumns(layer1);
        final int[][] sLayer_1 = surroundWithZeroRowsAndColumns(layer_1);

        final int[][] anLayer0 = calculateActiveNeighbours(sLayer0);
        final int[][] anLayer1 = calculateActiveNeighbours(sLayer1);
        final int[][] anLayer_1 = calculateActiveNeighbours(sLayer_1);

        int[][] result = new int[sLayer0.length][sLayer0[0].length];

        for (int r = 0; r < sLayer0.length; r++) {
            for (int c = 0; c < sLayer0[0].length; c++) {
                result[r][c] = anLayer0[r][c] + anLayer1[r][c] + anLayer_1[r][c];
                if (sLayer1[r][c] == 1) {
                    result[r][c] += 1;
                }
                if (sLayer_1[r][c] == 1) {
                    result[r][c] += 1;
                }
            }
        }
        return result;
    }

    private int[][] calculateActiveNeighbours(final int[][] input) {
        int[][] result = new int[input.length][input[0].length];

        final int[] flatInput = Arrays.stream(input).flatMapToInt(Arrays::stream).toArray();

        IntStream.range(0, input.length * input[0].length).forEach(index -> {
            final List<Integer> neighbourIndices = findNeighbourIndices(index, input.length, input[0].length);
            final int count = (int) neighbourIndices.stream().filter(i -> flatInput[i] == 1).count();

            int row = index / input[0].length;
            int col = index - (row * input[0].length);
            result[row][col] = count;
        });

        return result;
    }

    List<Integer> findNeighbourIndices(final int index, final int rows, final int cols) {
        List<Integer> result = new ArrayList<>();

        int row = index / cols;
        int col = index - (row * cols);

        if ((row - 1) >= 0) {
            if ((col - 1) >= 0) {
                result.add(index - (cols + 1));
            }

            result.add(index - cols);

            if ((col + 1) < cols) {
                result.add(index - (cols - 1));
            }
        }

        if ((col - 1) >= 0) {
            result.add(index - 1);
        }
        if ((col + 1) < cols) {
            result.add(index + 1);
        }

        if ((row + 1) < rows) {
            if ((col - 1) >= 0) {
                result.add(index + (cols - 1));
            }

            result.add(index + cols);

            if ((col + 1) < cols) {
                result.add(index + (cols + 1));
            }
        }

        return result;
    }

}
