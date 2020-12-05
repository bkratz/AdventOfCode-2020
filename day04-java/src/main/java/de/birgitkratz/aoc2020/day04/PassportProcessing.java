package de.birgitkratz.aoc2020.day04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PassportProcessing {

    private final KeyValueValidator keyValueValidator = new KeyValueValidator();
    private final Path path = Paths.get(Objects.requireNonNull(this.getClass().getClassLoader().getResource("input.txt")).getFile());

    long processPart1() throws IOException {
        return processPassportsPart1(readPassportsFromBatchFile(path));
    }

    long processPart2() throws IOException {
        return processPassportsPart2(readPassportsFromBatchFile(path));
    }

    long processPassportsPart1(final List<String> passports) {
        return passports.stream()
                .filter(this::validatePassportPart1)
                .count();
    }

    long processPassportsPart2(final List<String> passports) {
        return passports.stream()
                .filter(this::validatePassportPart2)
                .count();
    }

    List<String> readPassportsFromBatchFile(final Path tempFile) throws IOException {
        final List<String> lines = Files.readAllLines(tempFile);

        List<String> passports = new ArrayList<>();
        final String[] passport = {""};

        lines.forEach(line -> {
            if (line.isBlank()) {
                passports.add(passport[0].trim());
                passport[0] = "";
            }
            passport[0] += line.trim() + " ";
        });
        passports.add(passport[0].trim());
        return passports;
    }

    private boolean validatePassportPart1(String passport) {
        final String[] keyValuePairs = passport.split(" ");
        if (keyValuePairs.length == 8) {
            return true;
        }
        return keyValueValidator.validateRequiredKeys(keyValuePairs);
    }

    private boolean validatePassportPart2(String passport) {
        if (!validatePassportPart1(passport)) {
            return false;
        }

        final String[] keyValuePairs = passport.split(" ");
        return keyValueValidator.validateKeyValues(keyValuePairs);
    }
}
