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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;

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

    long processPart2() throws IOException {
        final List<String> lines = Files.readAllLines(path);
        return processInputPart2(lines);
    }

    long maskPart1(final long number, final String mask) {

        String maskForOr = mask.replaceAll("X", "0");
        String maskForAnd = mask.replaceAll("X", "1");

        final long or = Long.parseLong(maskForOr, 2);
        final long and = Long.parseLong(maskForAnd, 2);

        return ((number | or) & and);
    }

    String maskPart2(final long address, final String mask) {
        String addressBinaryString = Long.toBinaryString(address);
        addressBinaryString = StringUtils.leftPad(addressBinaryString, 36, "0");

        String result = StringUtils.leftPad("", 36, "0");

        final char[] maskChars = mask.toCharArray();
        char[] resultChars = result.toCharArray();

        for (int i=0; i<maskChars.length; i++) {
            resultChars[i] = addressBinaryString.charAt(i);
            if (maskChars[i] == '1') {
                resultChars[i] = '1';
            }
            if (maskChars[i] =='X') {
                resultChars[i] = 'X';
            }
        }

        return String.valueOf(resultChars);

    }


    void processInputGroupPart1(final List<String> inputGroup) {
        String mask = inputGroup.get(0).split("mask = ")[1];

        for (int i = 1; i < inputGroup.size(); i++) {
            Matcher m = pattern.matcher(inputGroup.get(i));

            if (m.find()) {
                long memPosition = Long.parseLong(m.group(1));
                long value = Long.parseLong(m.group(2));

                long result = maskPart1(value, mask);
                mem.put(memPosition, result);
            }
        }
    }

    void processInputGroupPart2(final List<String> inputGroup) {
        String mask = inputGroup.get(0).split("mask = ")[1];

        for (int i = 1; i < inputGroup.size(); i++) {
            Matcher m = pattern.matcher(inputGroup.get(i));

            if (m.find()) {
                long memPosition = Long.parseLong(m.group(1));
                long value = Long.parseLong(m.group(2));

                final String addressMask = maskPart2(memPosition, mask);
                final List<String> addresses = findAddressesPart2(addressMask);
                addresses.forEach(address -> mem.put(Long.parseLong(address, 2), value));
            }
        }
    }


    long processInputPart1(final List<String> input) {
        List<List<String>> groups = findInputGroups(input);

        groups.forEach(this::processInputGroupPart1);

        return mem.values().stream().mapToLong(l -> l).sum();
    }

    long processInputPart2(final List<String> input) {
        List<List<String>> groups = findInputGroups(input);

        groups.forEach(this::processInputGroupPart2);

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

    public List<String> findAddressesPart2(final String addressMask) {
        int countX = StringUtils.countMatches(addressMask, 'X');

        List<String> result = new ArrayList<>();
        final int pow = (int)Math.pow(2, countX);

        final List<String> collect =
                IntStream.range(0, pow).mapToObj(i -> StringUtils.leftPad(Integer.toBinaryString(i), countX, "0"))
                        .collect(Collectors.toList());

        for (int i=0; i<pow; i++) {
            final String[] split = collect.get(i).split("");
            String regexMask = addressMask;
            for (int j=0; j<countX; j++) {
                regexMask = RegExUtils.replaceFirst(regexMask, "X", split[j]);
            }
            result.add(regexMask);
        }

        return result;
    }
}
