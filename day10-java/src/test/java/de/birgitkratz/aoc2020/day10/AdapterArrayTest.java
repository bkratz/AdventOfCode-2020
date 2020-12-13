package de.birgitkratz.aoc2020.day10;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

class AdapterArrayTest {

    AdapterArray adapterArray = new AdapterArray();

    @Test
    void processPart1() throws IOException {
        assertThat(adapterArray.processPart1()).isEqualTo(2046);
    }

    @Test
    void processPart2() throws IOException {
        assertThat(adapterArray.processPart2()).isEqualTo(1157018619904L);
    }

    @Test
    void processInputPart1() {
        int[] input = new int[] {16, 10, 15, 5, 1, 11, 7, 19, 6, 12, 4};
        assertThat(adapterArray.processInputPart1(input)).isEqualTo(35);

        input = new int[] {28, 33, 18, 42, 31, 14, 46, 20, 48, 47, 24, 23, 49, 45, 19, 38, 39, 11, 1, 32, 25, 35, 8, 17, 7,
                9, 4, 2, 34, 10, 3};
        assertThat(adapterArray.processInputPart1(input)).isEqualTo(220);
    }

    @Test
    void processInputPart2() {
        int[] input = new int[] {16, 10, 15, 5, 1, 11, 7, 19, 6, 12, 4};

        Set<Integer> inputSet = Arrays.stream(input).boxed().collect(Collectors.toSet());
        assertThat(adapterArray.processInputPart2(inputSet)).isEqualTo(8L);

        input = new int[] {28, 33, 18, 42, 31, 14, 46, 20, 48, 47, 24, 23, 49, 45, 19, 38, 39, 11, 1, 32, 25, 35, 8, 17, 7,
                9, 4, 2, 34, 10, 3};
        inputSet = Arrays.stream(input).boxed().collect(Collectors.toSet());
        assertThat(adapterArray.processInputPart2(inputSet)).isEqualTo(19208L);
    }
}