package de.birgitkratz.aoc2020.day14;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DockingDataTest {

    DockingData dockingData = new DockingData();

    @Test
    void processPart1() throws IOException {
        assertThat(dockingData.processPart1()).isEqualTo(9615006043476L);
    }

    @Test
    void processPart2() throws IOException {
        assertThat(dockingData.processPart2()).isEqualTo(4275496544925L);
    }

    @Test
    void processInputPart1() {
        List<String> input = List.of(
                "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X",
                "mem[8] = 11",
                "mem[7] = 101",
                "mem[8] = 0",
                "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X",
                "mem[10] = 11",
                "mem[9] = 101",
                "mem[10] = 0"
        );

        assertThat(dockingData.processInputPart1(input)).isEqualTo(330L);
    }

    @Test
    void processInputPart2() {
        List<String> input = List.of(
                "mask = 000000000000000000000000000000X1001X",
                "mem[42] = 100",
                "mask = 00000000000000000000000000000000X0XX",
                "mem[26] = 1"
        );

        assertThat(dockingData.processInputPart2(input)).isEqualTo(208L);
    }

    @Test
    void processInputGroupPart1() {
        List<String> input = List.of(
                "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X",
                "mem[8] = 11",
                "mem[7] = 101",
                "mem[8] = 0"
        );

        dockingData.processInputGroupPart1(input);
        assertThat(dockingData.mem).containsEntry(8L, 64L);
        assertThat(dockingData.mem).containsEntry(7L, 101L);
    }

    @Test
    void maskPart1() {
        String mask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X";
        long number = 11L;
        assertThat(dockingData.maskPart1(number, mask)).isEqualTo(73L);

        number = 101L;
        assertThat(dockingData.maskPart1(number, mask)).isEqualTo(101L);

        number = 0L;
        assertThat(dockingData.maskPart1(number, mask)).isEqualTo(64L);
    }

    @Test
    void maskPart2() {
        String mask = "000000000000000000000000000000X1001X";
        long address = 11L;
        assertThat(dockingData.maskPart2(address, mask)).isEqualTo("000000000000000000000000000000X1101X");

        mask = "00000000000000000000000000000000X0XX";
        address = 26L;
        assertThat(dockingData.maskPart2(address, mask)).isEqualTo("00000000000000000000000000000001X0XX");
    }

    @Test
    void findAdressesPart2() {
        String addressMask = "000000000000000000000000000000X1101X";

        assertThat(dockingData.findAddressesPart2(addressMask)).containsExactlyInAnyOrder(
                "000000000000000000000000000000011010",
                "000000000000000000000000000000011011",
                "000000000000000000000000000000111010",
                "000000000000000000000000000000111011"
        );

        addressMask = "00000000000000000000000000000001X0XX";

        assertThat(dockingData.findAddressesPart2(addressMask)).containsExactlyInAnyOrder(
                "000000000000000000000000000000010000",
                "000000000000000000000000000000010001",
                "000000000000000000000000000000010010",
                "000000000000000000000000000000010011",
                "000000000000000000000000000000011000",
                "000000000000000000000000000000011001",
                "000000000000000000000000000000011010",
                "000000000000000000000000000000011011"
        );
    }

    @Test
    void processInputGroupPart2() {
        List<String> input = List.of(
                "mask = 000000000000000000000000000000X1001X",
                "mem[42] = 100"
        );

        dockingData.processInputGroupPart2(input);
        assertThat(dockingData.mem).containsEntry(26L, 100L);
        assertThat(dockingData.mem).containsEntry(27L, 100L);
        assertThat(dockingData.mem).containsEntry(58L, 100L);
        assertThat(dockingData.mem).containsEntry(59L, 100L);
    }

}