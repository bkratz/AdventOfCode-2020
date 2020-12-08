package de.birgitkratz.aoc2020.day07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class HandyHaversacks {

    private final Path path =
            Paths.get(Objects.requireNonNull(this.getClass().getClassLoader().getResource("input.txt")).getFile());

    private final List<Bag> allBags = new ArrayList<>();

    private static class Bag {

        private final String name;
        private final List<Bag> canContain = new ArrayList<>();

        public Bag(final String name) {
            this.name = name;
        }

        public void addCanContain(Bag childBag) {
            canContain.add(childBag);
        }

        public List<Bag> getCanContain() {
            return canContain;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final Bag bag = (Bag) o;
            return Objects.equals(name, bag.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    void createBags(List<String> lines) {
        lines.forEach(this::createBag);
    }

    private void createBag(final String line) {
        final String[] nameSplit = line.split(" bags contain ");
        String bagName = nameSplit[0];

        Bag bag = new Bag(bagName);
        if (allBags.contains(bag)) {
            bag = allBags.get(allBags.indexOf(bag));
        } else {
            allBags.add(bag);
        }

        String children = nameSplit[1];

        if (children.equals("no other bags.")) {
            return;
        }

        final String[] childrenSplit = children.split(", ");
        final Bag finalBag = bag;
        Arrays.stream(childrenSplit).forEach(s -> {
            final String[] childSplit = s.split(" ");

            Bag childBag = new Bag(childSplit[1] + " " + childSplit[2]);
            if (allBags.contains(childBag)) {
                childBag = allBags.get(allBags.indexOf(childBag));
            } else {
                allBags.add(childBag);
            }

            for (int i = 0; i < Integer.parseInt(childSplit[0]); i++) {
                finalBag.addCanContain(childBag);
            }
        });
    }

    long processPart1() throws IOException {
        final List<String> lines = Files.readAllLines(path);
        return processInputPart1(lines);
    }

    int processPart2() throws IOException {
        final List<String> lines = Files.readAllLines(path);
        return processInputPart2(lines);
    }

    long processInputPart1(final List<String> inputList) {
        createBags(inputList);

        final List<Bag> notEmptyBags =
                allBags.stream().filter(bag -> !bag.getCanContain().isEmpty()).collect(Collectors.toList());
        return notEmptyBags.stream().filter(this::containsShinyGold).count();
    }

    private boolean containsShinyGold(final Bag bag) {
        return bag.getCanContain()
                .stream()
                .distinct()
                .anyMatch(bag1 -> bag1.getName().equals("shiny gold") || containsShinyGold(bag1));
    }

    int processInputPart2(final List<String> lines) {
        createBags(lines);

        return bagsInside(allBags.get(allBags.indexOf(new Bag("shiny gold"))), 0);
    }

    private int bagsInside(final Bag start, int sum) {
        sum += start.getCanContain().size();
        for (Bag bag1 : start.getCanContain()) sum = bagsInside(bag1, sum);
        return sum;
    }

}


