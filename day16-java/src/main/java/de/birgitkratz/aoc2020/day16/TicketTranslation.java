package de.birgitkratz.aoc2020.day16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TicketTranslation {
    private final Path path =
            Paths.get(Objects.requireNonNull(this.getClass().getClassLoader().getResource("input.txt")).getFile());

    public long processPart1() throws IOException {
        final String input = Files.readString(path);

        return calculateScanningErrorRate(input);
    }

    public long processPart2() throws IOException {
        final String input = Files.readString(path);

        final Map<String, Integer> categoryToIndexMatching = findCategoryToIndexMatching(input);

        final List<Integer> ownTicketValues =
                Arrays.stream(getOwnTicketFromInput(input).split(","))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());

        Map<String, Integer> ownTicketCategoryValues = new HashMap<>();
        categoryToIndexMatching.forEach((category, index) -> ownTicketCategoryValues.put(category, ownTicketValues.get(index)));

        return ownTicketCategoryValues.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("departure"))
                .mapToLong(Map.Entry::getValue)
                .reduce(1L, (x, y) -> x * y);
    }

    private String getNearbyTicketsFromInput(final String input) {
        final String[] split = input.split("\nyour ticket:\n");

        return split[1].split("\nnearby tickets:\n")[1];
    }

    private String getOwnTicketFromInput(final String input) {
        final String[] split = input.split("\nyour ticket:\n");

        return split[1].split("\nnearby tickets:\n")[0].trim();
    }

    private String getCategoriesFromInput(final String input) {
        return input.split("\nyour ticket:\n")[0];
    }

    Set<Integer> parseValidNumbersFromRange(final String validRange) {
        final String[] split = validRange.split("-");
        return IntStream.rangeClosed(Integer.parseInt(split[0]), Integer.parseInt(split[1]))
                .boxed()
                .collect(Collectors.toSet());
    }

    Set<Integer> parseAllValidNumbers(final String input) {
        Set<Integer> validNumbers = new HashSet<>();
        input.lines().forEach(line -> {
            final String[] fieldSplit = line.split(": ");
            final String[] rangesSplit = fieldSplit[1].split(" or ");
            validNumbers.addAll(parseValidNumbersFromRange(rangesSplit[0]));
            validNumbers.addAll(parseValidNumbersFromRange(rangesSplit[1]));
        });
        return validNumbers;
    }

    List<Integer> findInvalidNumbersInNearbyTickets(final String input, final Set<Integer> validNumbers) {
        List<Integer> numbers = new ArrayList<>();
        input.lines().forEach(line -> numbers.addAll(
                Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).filter(i -> !validNumbers.contains(i))
                        .boxed()
                        .collect(Collectors.toList())));
        return numbers;
    }

    long calculateScanningErrorRate(final String input) {
        final Set<Integer> allValidNumbers = parseAllValidNumbers(getCategoriesFromInput(input));
        final List<Integer> invalidNumbersInNearbyTickets =
                findInvalidNumbersInNearbyTickets(getNearbyTicketsFromInput(input), allValidNumbers);

        return invalidNumbersInNearbyTickets.stream().mapToInt(i -> i).sum();
    }

    List<String> findValidNearbyTicktes(final String input) {
        final List<String> validNearbyTickets = new ArrayList<>();

        final Set<Integer> allValidNumbers = parseAllValidNumbers(getCategoriesFromInput(input));
        getNearbyTicketsFromInput(input).lines().forEach(line -> {
                    final List<Integer> collect =
                            Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).filter(i -> !allValidNumbers.contains(i))
                                    .boxed()
                                    .collect(Collectors.toList());
                    if (collect.isEmpty()) {
                        validNearbyTickets.add(line);
                    }
                }
        );
        return validNearbyTickets;
    }

    Map<String, Set<Integer>> mapCategoryValues(final String input) {
        Map<String, Set<Integer>> result = new LinkedHashMap<>();

        input.lines().forEach(line -> {
            final String[] split = line.split(": ");
            result.put(split[0], parseAllValidNumbers(line));
        });
        return result;
    }

    public Map<String, Integer> findCategoryToIndexMatching(final String input) {
        final List<String> validNearbyTicktes = findValidNearbyTicktes(input);

        final Map<String, Set<Integer>> categoryValues = mapCategoryValues(getCategoriesFromInput(input));

        List<List<Integer>> nearbyCategoryList = new ArrayList<>();
        for (int i=0; i<categoryValues.size(); i++) {
            nearbyCategoryList.add(new ArrayList<>());
        }

        validNearbyTicktes.forEach(nearbyTicketString -> {
            final List<Integer> collect = Arrays.stream(nearbyTicketString.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            for (int i=0; i<collect.size(); i++) {
                nearbyCategoryList.get(i).add(collect.get(i));
            }
        });

        Map<String, Integer> categoryToIndexMap = new HashMap<>();

        while(!categoryValues.isEmpty()) {
            Map<Integer, List<String>> matchCount = new HashMap<>();
            for (int i = 0; i < nearbyCategoryList.size(); i++) {
                final List<Integer> nearbyIndexValues = nearbyCategoryList.get(i);

                final int finalI = i;
                matchCount.put(i, new ArrayList<>());
                categoryValues.forEach((key, value) -> {
                    if (value.containsAll(nearbyIndexValues)) {
                        matchCount.get(finalI).add(key);
                    }
                });
            }

            final Map.Entry<Integer, List<String>> onlyMatch = matchCount.entrySet().stream()
                    .filter(e -> e.getValue().size() == 1)
                    .findFirst()
                    .orElseGet(null);

            categoryToIndexMap.put(onlyMatch.getValue().get(0), onlyMatch.getKey());
            categoryValues.remove(onlyMatch.getValue().get(0));
        }

        return categoryToIndexMap;
    }
}
