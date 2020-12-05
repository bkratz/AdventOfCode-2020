package de.birgitkratz.aoc2020.day01;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReportRepairTest
{

    private ReportRepair reportRepair;

    @BeforeEach
    void setUp() {
        reportRepair = new ReportRepair();
    }

    @Test
    void shouldReturnAddAllPossibilitiesInAnArrayAndReturnSumAndSummands() {
        int[] intArray = new int[] {1, 2, 3};

        Map<Pair<Integer, Integer>, Integer> expected = new HashMap<>();
        expected.put(Pair.of(1, 2), 3);
        expected.put(Pair.of(1, 3), 4);
        expected.put(Pair.of(2, 3), 5);

        assertThat(reportRepair.addPair(intArray)).isEqualTo(expected);
    }

    @Test
    void shouldReturnAddAllPossibilitiesOfThreeInAnArrayAndReturnSumAndSummands() {
        int[] intArray = new int[] {1, 2, 3, 4, 5, 6};

        Map<Triple<Integer, Integer, Integer>, Integer> expected = new HashMap<>();
        expected.put(Triple.of(1, 2, 3), 6);
        expected.put(Triple.of(1, 2, 4), 7);
        expected.put(Triple.of(1, 2, 5), 8);
        expected.put(Triple.of(1, 2, 6), 9);
        expected.put(Triple.of(1, 3, 4), 8);
        expected.put(Triple.of(1, 3, 5), 9);
        expected.put(Triple.of(1, 3, 6), 10);
        expected.put(Triple.of(1, 4, 5), 10);
        expected.put(Triple.of(1, 4, 6), 11);
        expected.put(Triple.of(1, 5, 6), 12);
        expected.put(Triple.of(2, 3, 4), 9);
        expected.put(Triple.of(2, 3, 5), 10);
        expected.put(Triple.of(2, 3, 6), 11);
        expected.put(Triple.of(2, 4, 5), 11);
        expected.put(Triple.of(2, 4, 6), 12);
        expected.put(Triple.of(2, 5, 6), 13);
        expected.put(Triple.of(3, 4, 5), 12);
        expected.put(Triple.of(3, 4, 6), 13);
        expected.put(Triple.of(3, 5, 6), 14);
        expected.put(Triple.of(4, 5, 6), 15);

        assertThat(reportRepair.addTriple(intArray)).isEqualTo(expected);
    }

    @Test
    void shouldFindResultWithSum4AndReturnPairOfSummands() {
        Map<Pair<Integer, Integer>, Integer> input = new HashMap<>();
        input.put(Pair.of(1, 2), 3);
        input.put(Pair.of(1, 3), 4);
        input.put(Pair.of(2, 3), 5);

        assertThat(reportRepair.findPairSum(4, input)).isEqualTo(Pair.of(1, 3));
    }

    @Test
    void shouldReturnPairProductOfSummands() {
        assertThat(reportRepair.pairProduct(Pair.of(2, 4))).isEqualTo(8);
    }
}
