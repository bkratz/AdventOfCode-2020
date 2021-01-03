package de.birgitkratz.aoc2020.day16;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TicketTranslationTest {

    TicketTranslation ticketTranslation = new TicketTranslation();

    @Test
    void processPart1() throws IOException {
        assertThat(ticketTranslation.processPart1()).isEqualTo(26026L);
    }

    @Test
    void processPart2() throws IOException {
        assertThat(ticketTranslation.processPart2()).isEqualTo(1305243193339L);
    }

    @Test
    void parseValidNumbersFromRange() {
        String validRange = "1-3";

        assertThat(ticketTranslation.parseValidNumbersFromRange(validRange)).containsExactlyInAnyOrder(1, 2, 3);
    }

    @Test
    void parseAllValidNumbers() {
        String input = "class: 1-3 or 5-7\n"
                + "row: 6-11 or 33-44\n"
                + "seat: 13-40 or 45-50";

        assertThat(ticketTranslation.parseAllValidNumbers(input))
                .containsExactlyInAnyOrder(1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
                        25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49,
                        50);
    }

    @Test
    void invalidNumbersInNearbyTickets() {
        String input = "7,3,47\n"
                + "40,4,50\n"
                + "55,2,20\n"
                + "38,6,12";

        Set<Integer> validNumbers = Set.of(1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
                25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49,
                50);

        assertThat(ticketTranslation.findInvalidNumbersInNearbyTickets(input, validNumbers)).containsExactlyInAnyOrder(4, 55, 12);
    }

    @Test
    void acceptancePart1() {
        String input = "class: 1-3 or 5-7\n"
                + "row: 6-11 or 33-44\n"
                + "seat: 13-40 or 45-50\n"
                + "\n"
                + "your ticket:\n"
                + "7,1,14\n"
                + "\n"
                + "nearby tickets:\n"
                + "7,3,47\n"
                + "40,4,50\n"
                + "55,2,20\n"
                + "38,6,12";

        assertThat(ticketTranslation.calculateScanningErrorRate(input)).isEqualTo(71L);
    }

    @Test
    void findValidNearbyTickets() {
        String input = "class: 1-3 or 5-7\n"
                + "row: 6-11 or 33-44\n"
                + "seat: 13-40 or 45-50\n"
                + "\n"
                + "your ticket:\n"
                + "7,1,14\n"
                + "\n"
                + "nearby tickets:\n"
                + "7,3,47\n"
                + "40,4,50\n"
                + "55,2,20\n"
                + "38,6,12";
        assertThat(ticketTranslation.findValidNearbyTicktes(input)).hasSize(1);

        input = "class: 0-1 or 4-19\n"
                + "row: 0-5 or 8-19\n"
                + "seat: 0-13 or 16-19\n"
                + "\n"
                + "your ticket:\n"
                + "11,12,13\n"
                + "\n"
                + "nearby tickets:\n"
                + "3,9,18\n"
                + "15,1,5\n"
                + "5,14,9";
        assertThat(ticketTranslation.findValidNearbyTicktes(input)).hasSize(3);
    }

    @Test
    void mapCategoryValues() {
        String input = "class: 1-3 or 5-7\n"
                + "row: 6-11 or 33-44\n"
                + "seat: 13-40 or 45-50";

        Map<String, Set<Integer>> expectedMap = Map.of("class", Set.of(1, 2, 3, 5, 6, 7),
                "row", Set.of(6, 7, 8, 9, 10, 11, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44),
                "seat",
                Set.of(13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37,
                        38, 39, 40, 45, 46, 47, 48, 49, 50));

        assertThat(ticketTranslation.mapCategoryValues(input)).isEqualTo(expectedMap);

        input = "class: 0-1 or 4-19\n"
                + "row: 0-5 or 8-19\n"
                + "seat: 0-13 or 16-19";

        expectedMap = Map.of("class", Set.of(0, 1, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19),
                "row", Set.of(0, 1, 2, 3, 4, 5, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19),
                "seat", Set.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 16, 17, 18, 19));

        assertThat(ticketTranslation.mapCategoryValues(input)).isEqualTo(expectedMap);
    }

    @Test
    void findCategoryToIndexMatching() {
        String input = "class: 0-1 or 4-19\n"
                + "row: 0-5 or 8-19\n"
                + "seat: 0-13 or 16-19\n"
                + "\n"
                + "your ticket:\n"
                + "11,12,13\n"
                + "\n"
                + "nearby tickets:\n"
                + "3,9,18\n"
                + "15,1,5\n"
                + "5,14,9";

        Map<String, Integer> expectedOrderMap = Map.of("row", 0, "class", 1, "seat", 2);

        assertThat(ticketTranslation.findCategoryToIndexMatching(input)).isEqualTo(expectedOrderMap);

    }
}