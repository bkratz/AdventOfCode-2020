package de.birgitkratz.aoc2020.day09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.copyOfRange;
import static java.util.Arrays.stream;

public class EncodingError {

    private final Path path =
            Paths.get(Objects.requireNonNull(this.getClass().getClassLoader().getResource("input.txt")).getFile());

    long processPart1() throws IOException {
        final List<Long> lines = Files.readAllLines(path).stream()
                .map(Long::parseLong)
                .collect(Collectors.toList());

        return processInputPart1(lines, 25);
    }

    long processPart2(long targetSum) throws IOException {
        final List<Long> lines = Files.readAllLines(path).stream()
                .map(Long::parseLong)
                .collect(Collectors.toList());

        return processInputPart2(lines, targetSum);
    }

    long processInputPart1(final List<Long> input, final int preamble) {
        int index = IntStream.range(preamble, input.size())
                .filter(i -> !calculatePossibleSums(input, preamble, i - preamble).contains(input.get(i)))
                .findFirst()
                .orElse(-1);

        return input.get(index);
    }

    long processInputPart2(final List<Long> input, final long targetSum) {
        int windowIndexToExclusive = 2;
        int windowIndexFrom = 0;
        long[] inputArray = input.stream().mapToLong(l -> l).toArray();
        do {
            long windowSum = stream(inputArray, windowIndexFrom, windowIndexToExclusive).sum();
            if (windowSum == targetSum) {
                return stream(copyOfRange(inputArray, windowIndexFrom, windowIndexToExclusive)).min().getAsLong()
                        + stream(copyOfRange(inputArray, windowIndexFrom, windowIndexToExclusive)).max().getAsLong();
            }
            windowIndexToExclusive++;
            if (windowSum > targetSum) {
                windowIndexToExclusive = 2 + ++windowIndexFrom;
                windowIndexFrom = windowIndexToExclusive - 2;
            }
        } while (windowIndexToExclusive < input.size());
        return -1L;
    }

    Set<Long> calculatePossibleSums(final List<Long> input, final int preamble, final int startIndex) {
        Set<Long> results = new HashSet<>();

        IntStream.range(startIndex, startIndex + preamble - 1)
                .forEach(i -> IntStream.range(i + 1, startIndex + preamble)
                        .forEach(j -> {
                            if (!input.get(i).equals(input.get(j))) {
                                results.add(input.get(i) + input.get(j));
                            }
                        })
                );
        return results;
    }
}
