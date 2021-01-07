package de.birgitkratz.aoc2020.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;

public class AdapterArray {

    private final Path path =
            Paths.get(Objects.requireNonNull(this.getClass().getClassLoader().getResource("input.txt")).getFile());

    public int processPart1() throws IOException {
        final int[] input = Files.readAllLines(path).stream()
                .mapToInt(Integer::parseInt)
                .toArray();

        return processInputPart1(input);
    }

    public long processPart2() throws IOException {
        final Set<Integer> inputSet = Files.readAllLines(path).stream()
                .mapToInt(Integer::parseInt).boxed().collect(Collectors.toSet());

        return processInputPart2(inputSet);
    }

    public int processInputPart1(final int[] input) {
        int currentJolt = 0;
        int maxAdapterJolt = 3;

        int[] inputWithBuiltInJoltage = Arrays.stream(addBuiltInJoltage(input)).sorted().toArray();

        Map<Integer, Integer> differences = new HashMap<>();

        for (int i = 1; i < inputWithBuiltInJoltage.length; i++) {
            final int finalCurrentJolt = currentJolt;
            final int finalMaxAdapterJolt = maxAdapterJolt;
            final int currentAdapter =
                    Arrays.stream(inputWithBuiltInJoltage)
                            .filter(in -> in >= finalCurrentJolt + 1 && in <= finalMaxAdapterJolt)
                            .min()
                            .getAsInt();
            differences.merge(currentAdapter - currentJolt, 1, (value, v) -> value + 1);
            currentJolt = currentAdapter;
            maxAdapterJolt = currentJolt + 3;
        }

        return differences.values().stream().reduce((integer, integer2) -> integer * integer2).get();
    }

    private int[] addBuiltInJoltage(final int[] input) {
        final int max = Arrays.stream(input).max().getAsInt();

        final int[] add = ArrayUtils.add(input, max + 3);
        return ArrayUtils.insert(0, add, 0);
    }

    public long processInputPart2(final Set<Integer> input) {
        final int max = input.stream().mapToInt(i -> i).max().getAsInt();

        return countPaths(input, new HashMap<>(), 0, max+3);
    }

    private static long countPaths(Set<Integer> adapters, Map<Integer, Long> memo, int current, int target) {
        if (current + 3 == target) {
            return 1;
        } else if (memo.containsKey(current)) {
            return memo.get(current);
        }

        var count = 0L;
        if (adapters.contains(current + 1)) {
            count += countPaths(adapters, memo, current + 1, target);
        }
        if (adapters.contains(current + 2)) {
            count += countPaths(adapters, memo, current + 2, target);
        }
        if (adapters.contains(current + 3)) {
            count += countPaths(adapters, memo, current + 3, target);
        }
        memo.put(current, count);
        return count;
    }
}
