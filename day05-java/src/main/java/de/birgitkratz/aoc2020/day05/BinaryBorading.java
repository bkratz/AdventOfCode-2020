package de.birgitkratz.aoc2020.day05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import org.apache.commons.lang3.Range;

class BinaryBorading {

    private final Range<Integer> allRows = Range.between(0, 127);
    private final Range<Integer> allColumns = Range.between(0, 7);

    private final Path path =
            Paths.get(Objects.requireNonNull(this.getClass().getClassLoader().getResource("input.txt")).getFile());

    int processPart1() throws IOException {
        final List<String> lines = Files.readAllLines(path);

        return lines.stream()
                .mapToInt(this::processBinarySeatPart1)
                .max().orElse(-1);
    }

    int processPart2() throws IOException {
        final List<String> lines = Files.readAllLines(path);

        final int[] ints = lines.stream().mapToInt(this::processBinarySeatPart1).sorted().toArray();

        final int minSeatId = Arrays.stream(ints).min().getAsInt();
        final int maxSeatId = Arrays.stream(ints).max().getAsInt();

        final int sumActualSeatIds = Arrays.stream(ints).sum();

        final int sumAllSeatIds = IntStream.rangeClosed(minSeatId, maxSeatId).sum();

        return sumAllSeatIds - sumActualSeatIds;
    }


    int processBinarySeatPart1(final String binarySeat) {
        int row = processRowPart1(binarySeat.substring(0, 7));
        int columnn = processColumnPart1(binarySeat.substring(7));

        return row * 8 + columnn;
    }

    int processRowPart1(final String binaryRow) {
        final Range<Integer> integerRange = processBinaryString(binaryRow, allRows, 0);
        return integerRange.getMinimum();
    }

    int processColumnPart1(final String binaryColumn) {
        final Range<Integer> integerRange = processBinaryString(binaryColumn, allColumns, 0);
        return integerRange.getMinimum();
    }

    private Range<Integer> processBinaryString(final String binaryString, Range<Integer> range, int startPosition) {
        if (startPosition == binaryString.length()) {
            return range;
        }
        return (processBinaryString(binaryString, rangeSplitter(range, binaryString.charAt(startPosition)),
                startPosition + 1));
    }

    private Range<Integer> rangeSplitter(Range<Integer> originalRange, char position) {
        if (position == 'F' || position == 'L') {
            return Range.between(originalRange.getMinimum(), (originalRange.getMinimum() + originalRange.getMaximum()) / 2);
        }

        return Range
                .between(((originalRange.getMinimum() + originalRange.getMaximum()) / 2) + 1, originalRange.getMaximum());
    }

}
