package de.birgitkratz.aoc2020.day05;

import java.io.IOException;

import org.apache.commons.lang3.Range;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BinaryBoradingTest {

    BinaryBorading binaryBorading = new BinaryBorading();

    @Test
    void processPart1() throws IOException {
        assertThat(binaryBorading.processPart1()).isEqualTo(915);
    }

    @Test
    void processPart2() throws IOException {
        assertThat(binaryBorading.processPart2()).isEqualTo(699);
    }

    @Test
    void acceptancePart1() {
        assertThat(binaryBorading.processBinarySeatPart1("BFFFBBFRRR")).isEqualTo(567);
        assertThat(binaryBorading.processBinarySeatPart1("FFFBBBFRRR")).isEqualTo(119);
        assertThat(binaryBorading.processBinarySeatPart1("BBFFBBFRLL")).isEqualTo(820);
    }

    @Test
    void processRowPart1() {
        assertThat(binaryBorading.processRowPart1("BFFFBBF")).isEqualTo(70);
        assertThat(binaryBorading.processRowPart1("FFFBBBF")).isEqualTo(14);
        assertThat(binaryBorading.processRowPart1("BBFFBBF")).isEqualTo(102);
    }

    @Test
    void processColumnPart1() {
        assertThat(binaryBorading.processColumnPart1("RRR")).isEqualTo(7);
        assertThat(binaryBorading.processColumnPart1("RLL")).isEqualTo(4);
    }
}