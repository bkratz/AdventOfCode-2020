package de.birgitkratz.aoc2020.day15;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class RumbunctiousRecitation {

    int solvePart1(String input) {
        return solve(input, 2020);
    }

    int solvePart2(String input) {
        return solve(input, 30000000);
    }

    int solve (String input, int turns) {
        final String[] split = input.split(",");
        Map<Integer, Integer> spokenMap = new HashMap<>();

        IntStream.range(1, split.length).forEach(i ->spokenMap.put(Integer.parseInt(split[i-1]), i));

        return getNthTurnNumber(turns, spokenMap, split.length, Integer.parseInt(split[split.length - 1]));
    }

    int getNextTurnNumber(final int previousTurn, final int previousNumber, final Map<Integer, Integer> spokenMap) {
        final Integer alreadySpoken = spokenMap.get(previousNumber);
        spokenMap.put(previousNumber, previousTurn);

        if (alreadySpoken == null) {
            return 0;
        }
        return previousTurn - alreadySpoken;
    }

    int getNthTurnNumber(int turns, Map<Integer, Integer> spokenMap, final int previousTurn, final int previousNumber) {
        int number = previousNumber;
        for (int i = previousTurn; i<turns; i++) {
            number = getNextTurnNumber(i, number, spokenMap);
        }
        return number;
    }
}
