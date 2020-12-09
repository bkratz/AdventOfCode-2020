package de.birgitkratz.aoc2020.day08;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HandheldHaltingTest {

    HandheldHalting handheldHalting = new HandheldHalting();

    @Test
    void processPart1() throws IOException {
        assertThat(handheldHalting.processPart1()).isEqualTo(1915);
    }

    @Test
    void processPart2() throws IOException {
        assertThat(handheldHalting.processPart2()).isEqualTo(944);
    }

    @Test
    void parseInstructions() {
        String input = "nop +0\n"
                + "acc +1\n"
                + "jmp +4\n"
                + "acc +3\n"
                + "jmp -3\n"
                + "acc -99\n"
                + "acc +1\n"
                + "jmp -4\n"
                + "acc +6";

        final List<String> inputList = input.lines().collect(Collectors.toList());
        handheldHalting.parseInstructions(inputList);
        assertThat(handheldHalting.instructions)
                .containsExactly(new NopInstruction(0),
                        new AccInstruction(1),
                        new JmpInstruction(4),
                        new AccInstruction(3),
                        new JmpInstruction(-3),
                        new AccInstruction(-99),
                        new AccInstruction(1),
                        new JmpInstruction(-4),
                        new AccInstruction(6)
                );
    }

    @Test
    void processInstructionsPart1() {
        handheldHalting.instructions.addAll(
                List.of(new NopInstruction(0),
                        new AccInstruction(1),
                        new JmpInstruction(4),
                        new AccInstruction(3),
                        new JmpInstruction(-3),
                        new AccInstruction(-99),
                        new AccInstruction(1),
                        new JmpInstruction(-4),
                        new AccInstruction(6)
                )
        );

        assertThat(handheldHalting.processInstructionsPart1()).isEqualTo(5);
    }

    @Test
    void processInstructionsPart2() {
        handheldHalting.instructions.addAll(
                List.of(new NopInstruction(0),
                        new AccInstruction(1),
                        new JmpInstruction(4),
                        new AccInstruction(3),
                        new JmpInstruction(-3),
                        new AccInstruction(-99),
                        new AccInstruction(1),
                        new JmpInstruction(-4),
                        new AccInstruction(6)
                )
        );

        assertThat(handheldHalting.processInstructionsPart2()).isEqualTo(8);
    }
}