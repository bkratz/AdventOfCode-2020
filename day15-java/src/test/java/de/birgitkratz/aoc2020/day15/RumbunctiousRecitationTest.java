package de.birgitkratz.aoc2020.day15;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RumbunctiousRecitationTest {
    RumbunctiousRecitation rumbunctiousRecitation = new RumbunctiousRecitation();

    @Test
    void solvePart1() {
        assertThat(rumbunctiousRecitation.solvePart1("1,0,16,5,17,4")).isEqualTo(1294);
    }

    @Test
    void solvePart2() {
        assertThat(rumbunctiousRecitation.solvePart2("1,0,16,5,17,4")).isEqualTo(573522);
    }

    @Test
    void acceptanceTestPart1() {
        assertThat(rumbunctiousRecitation.solvePart1("1,3,2")).isEqualTo(1);
        assertThat(rumbunctiousRecitation.solvePart1("2,1,3")).isEqualTo(10);
        assertThat(rumbunctiousRecitation.solvePart1("3,1,2")).isEqualTo(1836);
    }

    @Test
    void getNextTurnNumber() {
        Map<Integer, Integer> spokenMap = new HashMap<>();
        spokenMap.put(0, 1);
        spokenMap.put(3, 2);

        assertThat(rumbunctiousRecitation.getNextTurnNumber(3, 6,  spokenMap)).isEqualTo(0);
        assertThat(spokenMap).containsEntry(6, 3);

        assertThat(rumbunctiousRecitation.getNextTurnNumber(4, 0, spokenMap)).isEqualTo(3);
        assertThat(spokenMap).containsEntry(0, 4);

        assertThat(rumbunctiousRecitation.getNextTurnNumber(5, 3, spokenMap)).isEqualTo(3);
        assertThat(spokenMap).containsEntry(3, 5);

        assertThat(rumbunctiousRecitation.getNextTurnNumber(6, 3, spokenMap)).isEqualTo(1);
        assertThat(spokenMap).containsEntry(3, 6);

        assertThat(rumbunctiousRecitation.getNextTurnNumber(7, 1, spokenMap)).isEqualTo(0);
        assertThat(spokenMap).containsEntry(1, 7);

        assertThat(rumbunctiousRecitation.getNextTurnNumber(8, 0, spokenMap)).isEqualTo(4);
        assertThat(spokenMap).containsEntry(0, 8);

        assertThat(rumbunctiousRecitation.getNextTurnNumber(9, 4, spokenMap)).isEqualTo(0);
        assertThat(spokenMap).containsEntry(4, 9);
    }

    @Test
    void get2020thTurnNumber() {
        Map<Integer, Integer> spokenMap = new HashMap<>();
        spokenMap.put(0, 1);
        spokenMap.put(3, 2);
        assertThat(rumbunctiousRecitation.getNthTurnNumber(2020, spokenMap, 3, 6)).isEqualTo(436);

        spokenMap = new HashMap<>();
        spokenMap.put(1, 1);
        spokenMap.put(3, 2);
        assertThat(rumbunctiousRecitation.getNthTurnNumber(2020, spokenMap, 3, 2)).isEqualTo(1);

        spokenMap = new HashMap<>();
        spokenMap.put(2, 1);
        spokenMap.put(1, 2);
        assertThat(rumbunctiousRecitation.getNthTurnNumber(2020, spokenMap, 3, 3)).isEqualTo(10);

        spokenMap = new HashMap<>();
        spokenMap.put(1, 1);
        spokenMap.put(2, 2);
        assertThat(rumbunctiousRecitation.getNthTurnNumber(2020, spokenMap, 3, 3)).isEqualTo(27);

        spokenMap = new HashMap<>();
        spokenMap.put(2, 1);
        spokenMap.put(3, 2);
        assertThat(rumbunctiousRecitation.getNthTurnNumber(2020, spokenMap, 3, 1)).isEqualTo(78);

        spokenMap = new HashMap<>();
        spokenMap.put(3, 1);
        spokenMap.put(2, 2);
        assertThat(rumbunctiousRecitation.getNthTurnNumber(2020, spokenMap, 3, 1)).isEqualTo(438);

        spokenMap = new HashMap<>();
        spokenMap.put(3, 1);
        spokenMap.put(1, 2);
        assertThat(rumbunctiousRecitation.getNthTurnNumber(2020, spokenMap, 3, 2)).isEqualTo(1836);
    }

    @Test
    void get30000000thTurnNumber() {
        Map<Integer, Integer> spokenMap = new HashMap<>();
        spokenMap.put(0, 1);
        spokenMap.put(3, 2);
        assertThat(rumbunctiousRecitation.getNthTurnNumber(30000000, spokenMap, 3, 6)).isEqualTo(175594);

        spokenMap = new HashMap<>();
        spokenMap.put(1, 1);
        spokenMap.put(3, 2);
        assertThat(rumbunctiousRecitation.getNthTurnNumber(30000000, spokenMap, 3, 2)).isEqualTo(2578);

        spokenMap = new HashMap<>();
        spokenMap.put(2, 1);
        spokenMap.put(1, 2);
        assertThat(rumbunctiousRecitation.getNthTurnNumber(30000000, spokenMap, 3, 3)).isEqualTo(3544142);

        spokenMap = new HashMap<>();
        spokenMap.put(1, 1);
        spokenMap.put(2, 2);
        assertThat(rumbunctiousRecitation.getNthTurnNumber(30000000, spokenMap, 3, 3)).isEqualTo(261214);

        spokenMap = new HashMap<>();
        spokenMap.put(2, 1);
        spokenMap.put(3, 2);
        assertThat(rumbunctiousRecitation.getNthTurnNumber(30000000, spokenMap, 3, 1)).isEqualTo(6895259);

        spokenMap = new HashMap<>();
        spokenMap.put(3, 1);
        spokenMap.put(2, 2);
        assertThat(rumbunctiousRecitation.getNthTurnNumber(30000000, spokenMap, 3, 1)).isEqualTo(18);

        spokenMap = new HashMap<>();
        spokenMap.put(3, 1);
        spokenMap.put(1, 2);
        assertThat(rumbunctiousRecitation.getNthTurnNumber(30000000, spokenMap, 3, 2)).isEqualTo(362);
    }
}