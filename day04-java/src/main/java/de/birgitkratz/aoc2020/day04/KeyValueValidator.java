package de.birgitkratz.aoc2020.day04;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class KeyValueValidator {
    private static final List<String> REQUIRED_KEYS = List.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");

    boolean validateRequiredKeys(final String[] keyValuePairs) {
        final List<String> listOfKeys =
                Arrays.stream(keyValuePairs)
                        .map(keyValue -> keyValue.split(":")[0])
                        .collect(Collectors.toList());
        return listOfKeys.containsAll(REQUIRED_KEYS);
    }

    boolean validateKeyValues(final String[] keyValuePairs) {
        final List<Boolean> collect = Arrays.stream(keyValuePairs)
                .map(this::validateKeyValues)
                .collect(Collectors.toList());
        return !collect.contains(false);
    }

    boolean validateKeyValues(String keyValue) {
        final String[] split = keyValue.split(":");
        return switch (split[0]) {
            case "byr" -> validateBYR(split[1]);
            case "iyr" -> validateIYR(split[1]);
            case "eyr" -> validateEYR(split[1]);
            case "hgt" -> validateHGT(split[1]);
            case "hcl" -> validateHCL(split[1]);
            case "ecl" -> validateECL(split[1]);
            case "pid" -> validatePID(split[1]);
            case "cid" -> true;
            default -> false;
        };
    }

    boolean validateBYR(final String value) {
        try {
            int year = Integer.parseInt(value);
            return (year >= 1920 && year <= 2002);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    boolean validateIYR(final String value) {
        try {
            int year = Integer.parseInt(value);
            return (year >= 2010 && year <= 2020);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    boolean validateEYR(final String value) {
        try {
            int year = Integer.parseInt(value);
            return (year >= 2020 && year <= 2030);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    boolean validateHGT(final String value) {
        try {
            final int height = Integer.parseInt(value.substring(0, value.length() - 2));
            if (value.endsWith("in")) {
                return (height >= 59 && height <= 76);
            }
            if (value.endsWith("cm")) {
                return (height >= 150 && height <= 193);
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }

    boolean validateHCL(final String value) {
        return value.matches("#[0-9a-f]{6}");
    }

    boolean validateECL(final String value) {
        return value.matches("amb|blu|brn|gry|grn|hzl|oth");
    }

    boolean validatePID(final String value) {
        return value.matches("[0-9]{9}");
    }
}
