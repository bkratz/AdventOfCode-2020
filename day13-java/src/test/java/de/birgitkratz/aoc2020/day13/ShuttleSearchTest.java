package de.birgitkratz.aoc2020.day13;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ShuttleSearchTest {

    ShuttleSearch shuttleSearch = new ShuttleSearch();

    @Test
    void processPart1() throws IOException {
        assertThat(shuttleSearch.processPart1()).isEqualTo(3035L);
    }

    @Test
    void processPart2() throws IOException {
        assertThat(shuttleSearch.processPart2()).isEqualTo(725169163285238L);
    }
    @Test
    void processInputPart1() {
        List<String> input = List.of("939", "7,13,x,x,59,x,31,19");

        assertThat(shuttleSearch.processInputPart1(input)).isEqualTo(295L);
    }
    @Test
    void processInputPart2() {
        List<String> input = List.of("939", "17,x,13,19");
        assertThat(shuttleSearch.processInputPart2(input)).isEqualTo(3417L);

        input = List.of("939", "67,7,59,61");
        assertThat(shuttleSearch.processInputPart2(input)).isEqualTo(754018L);

        input = List.of("939", "67,x,7,59,61");
        assertThat(shuttleSearch.processInputPart2(input)).isEqualTo(779210L);
    }
}