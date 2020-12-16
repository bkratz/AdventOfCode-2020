package de.birgitkratz.aoc2020.day14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DockingData {

    private static final String REGEX = "mem\\[(\\d+)] = (\\d+)";

    Pattern pattern = Pattern.compile(REGEX);
    Map<Long, Long> mem = new HashMap<>();

    private final Path path =
            Paths.get(Objects.requireNonNull(this.getClass().getClassLoader().getResource("input.txt")).getFile());

    long processPart1() throws IOException {
        final List<String> lines = Files.readAllLines(path);
        return processInputPart1(lines);
    }

    long mask(final long number, final String mask) {

        String maskForOr = mask.replaceAll("X", "0");
        String maskForAnd = mask.replaceAll("X", "1");

        final long or = Long.parseLong(maskForOr, 2);
        final long and = Long.parseLong(maskForAnd, 2);

        return ((number | or) & and);
    }

    void processInputGroupPart1(final List<String> inputGroup) {
        String mask = inputGroup.get(0).split("mask = ")[1];

        for (int i = 1; i < inputGroup.size(); i++) {
            Matcher m = pattern.matcher(inputGroup.get(i));

            if (m.find()) {
                long memPosition = Long.parseLong(m.group(1));
                long value = Long.parseLong(m.group(2));

                long result = mask(value, mask);
                mem.put(memPosition, result);
            }
        }
    }

    long processInputPart1(final List<String> input) {
        List<List<String>> groups = findInputGroups(input);

        groups.forEach(this::processInputGroupPart1);

        return mem.values().stream().mapToLong(l -> l).sum();
    }

    private List<List<String>> findInputGroups(final List<String> input) {
        List<List<String>> groups = new ArrayList<>();
        List<String> group = new ArrayList<>();

        input.forEach(s -> {
            if (s.startsWith("mask =")) {
                if (!group.isEmpty()) {
                    groups.add(List.copyOf(group));
                }
                group.clear();
            }
            group.add(s);
        });
        if (!group.isEmpty()) {
            groups.add(List.copyOf(group));
        }
        return groups;
    }
}
