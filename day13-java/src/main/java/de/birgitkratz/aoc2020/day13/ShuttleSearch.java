package de.birgitkratz.aoc2020.day13;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ShuttleSearch {
    private final Path path =
            Paths.get(Objects.requireNonNull(this.getClass().getClassLoader().getResource("input.txt")).getFile());

    public long processPart1() throws IOException {
        final List<String> lines = Files.readAllLines(path);
        return processInputPart1(lines);
    }

    public long processPart2() throws IOException {
        final List<String> lines = Files.readAllLines(path);
        return processInputPart2(lines);
    }

    long processInputPart1(final List<String> input) {
        final Long earliestTimestamp = Long.parseLong(input.get(0));
        final List<Integer> busIds =
                Arrays.stream(input.get(1).split(","))
                        .filter(s -> !s.equals("x"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());

        Map<Integer, Long> busIdToNextDepartureMap = new HashMap<>();
        busIds.forEach(busId -> busIdToNextDepartureMap.put(busId, busId - earliestTimestamp % busId));

        Map.Entry<Integer, Long> minByValyeEntry = Collections.min(busIdToNextDepartureMap.entrySet(),
                Map.Entry.comparingByValue());

        return minByValyeEntry.getValue() * minByValyeEntry.getKey();
    }

    long processInputPart2(final List<String> input) {
        List<BigInteger> as = new ArrayList<>();
        List<BigInteger> qs = new ArrayList<>();

        final String[] split = input.get(1).split(",");
        for (int i=0; i<split.length; i++) {
            if (split[i].equals("x")) {
                continue;
            }
            as.add(BigInteger.valueOf(-i));
            qs.add(BigInteger.valueOf(Long.parseLong(split[i])));
        }

        final BigInteger result = CRT.chinese_remainder_theorem(as, qs, as.size());
        return result.longValue();
    }
}
