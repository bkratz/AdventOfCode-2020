package de.birgitkratz.aoc2020.day06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class CustomCustoms {

    private final Path path =
            Paths.get(Objects.requireNonNull(this.getClass().getClassLoader().getResource("input.txt")).getFile());

    int processPart1() throws IOException {
        final List<String> lines = Files.readAllLines(path);

        return groupInput1(lines).stream()
                .map(s -> s.chars()
                        .mapToObj(i -> Character.toString((char) i))
                        .distinct()
                        .collect(Collectors.joining()))
                .mapToInt(String::length)
                .sum();
    }

    int processPart2() throws IOException {
        final List<String> lines = Files.readAllLines(path);

        return groupInput2(lines).stream()
                .mapToInt(String::length)
                .sum();
    }

    List<String> groupInput1(final List<String> input) {
        List<String> groups = new ArrayList<>();
        final String[] group = {""};

        input.forEach(line -> {
            if (line.isBlank()) {
                groups.add(group[0].trim());
                group[0] = "";
            } else {
                group[0] += line.trim();
            }
        });
        groups.add(group[0].trim());
        return groups;
    }

    List<String> groupInput2(final List<String> input) {
        List<String> groups = new ArrayList<>();

        final AtomicInteger index = new AtomicInteger();
        final List<String>[] group = new List[] {new ArrayList<>()};
        input.forEach(line -> {
            if (!line.isBlank()) {
                if (index.get() == 0) {
                    group[0] = stringToListOfCharacterStrings(line);
                } else {
                    group[0].retainAll(stringToListOfCharacterStrings(line));
                }
                index.getAndIncrement();
            } else {
                groups.add(listOfCharacterStringsToString(group[0]));
                index.set(0);
                group[0] = new ArrayList<>();
            }
        });
        groups.add(listOfCharacterStringsToString(group[0]));
        return groups;
    }

    private String listOfCharacterStringsToString(final List<String> elements) {
        return String.join("", elements);
    }

    private List<String> stringToListOfCharacterStrings(final String line) {
        return line.chars()
                .mapToObj(i -> String.valueOf((char) i))
                .collect(Collectors.toList());
    }
}
