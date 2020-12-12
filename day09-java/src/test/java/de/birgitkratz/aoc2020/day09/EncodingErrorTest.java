package de.birgitkratz.aoc2020.day09;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EncodingErrorTest {

    EncodingError encodingError = new EncodingError();

    @Test
    void processPart1() throws IOException {
        assertThat(encodingError.processPart1()).isEqualTo(756008079L);
    }

    @Test
    void processPart2() throws IOException {
        assertThat(encodingError.processPart2(756008079L)).isEqualTo(93727241L);
    }

    @Test
    void processInputPart1() {
        List<Long> input =
                List.of(35L, 20L, 15L, 25L, 47L, 40L, 62L, 55L, 65L, 95L, 102L, 117L, 150L, 182L, 127L, 219L, 299L, 277L, 309L, 576L);

        int preamble = 5;
        assertThat(encodingError.processInputPart1(input, preamble)).isEqualTo(127L);
    }

    @Test
    void processInputPart2() {
        List<Long> input =
                List.of(35L, 20L, 15L, 25L, 47L, 40L, 62L, 55L, 65L, 95L, 102L, 117L, 150L, 182L, 127L, 219L, 299L, 277L, 309L, 576L);

        assertThat(encodingError.processInputPart2(input, 127L)).isEqualTo(62L);
    }

    @Test
    void calculateAllPossibleSumsFromListOfNumbersWithPreambleSize5() {
        List<Long> input =
                List.of(35L, 20L, 15L, 25L, 47L, 40L, 62L, 55L, 65L, 95L, 102L, 117L, 150L, 182L, 127L, 219L, 299L, 277L, 309L, 576L);

        int preamble = 5;
        int startIndex = 0;
        assertThat(encodingError.calculatePossibleSums(input, preamble, startIndex)).hasSize(10);
    }
}