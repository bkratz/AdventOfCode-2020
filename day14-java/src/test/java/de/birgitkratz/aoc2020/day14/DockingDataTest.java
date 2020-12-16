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
    void mask() {
        String mask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X";
        long number = 11L;
        assertThat(dockingData.mask(number, mask)).isEqualTo(73L);

        number = 101L;
        assertThat(dockingData.mask(number, mask)).isEqualTo(101L);

        number = 0L;
        assertThat(dockingData.mask(number, mask)).isEqualTo(64L);
    }
}