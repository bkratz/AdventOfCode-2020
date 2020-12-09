package de.birgitkratz.aoc2020.day08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class HandheldHalting {
    private final Path path =
            Paths.get(Objects.requireNonNull(this.getClass().getClassLoader().getResource("input.txt")).getFile());

    List<Instruction> instructions = new ArrayList<>();

    public int processPart1() throws IOException {
        final List<String> lines = Files.readAllLines(path);
        parseInstructions(lines);
        return processInstructionsPart1();
    }

    public int processPart2() throws IOException {
        final List<String> lines = Files.readAllLines(path);
        parseInstructions(lines);
        return processInstructionsPart2();
    }

    public void parseInstructions(final List<String> inputList) {
        inputList.forEach(line -> {
            final String[] s = line.split(" ");
            switch (s[0]) {
                case "nop" -> instructions.add(new NopInstruction(Integer.parseInt(s[1])));
                case "acc" -> instructions.add(new AccInstruction(Integer.parseInt(s[1])));
                case "jmp" -> instructions.add(new JmpInstruction(Integer.parseInt(s[1])));
            }
        });
    }

    public int processInstructionsPart1() {
        int index = 0;
        Set<Integer> visitedIndex = new HashSet<>();
        int accumulator = 0;
        while (visitedIndex.add(index)) {
            final Instruction instruction = instructions.get(index);
            if (instruction instanceof AccInstruction) {
                accumulator = ((AccInstruction) instruction).accumulate(accumulator);
            }
            index = instruction.processIndex(index);
        }
        return accumulator;
    }

    public int processInstructionsPart2() {
        int index = 0;
        Set<Integer> visitedIndex = new HashSet<>();
        int accumulator = 0;
        for (int i=0; i<instructions.size(); i++) {
            if (index >= instructions.size()) {
                break;
            }

            List<Instruction> copyInstructions = new ArrayList<>(instructions);
            switchInstructions(i, copyInstructions);

            visitedIndex.clear();
            index = 0;
            accumulator = 0;
            while (visitedIndex.add(index) && index < copyInstructions.size()) {
                final Instruction instruction = copyInstructions.get(index);
                if (instruction instanceof AccInstruction) {
                    accumulator = ((AccInstruction) instruction).accumulate(accumulator);
                }
                index = instruction.processIndex(index);
            }
        }
        return accumulator;
    }

    private void switchInstructions(final int i, final List<Instruction> copyInstructions) {
        final Instruction switchInstruction = copyInstructions.get(i);
        if (switchInstruction instanceof NopInstruction) {
            copyInstructions.set(i, new JmpInstruction(switchInstruction.getValue() == 0 ? 1 : switchInstruction.getValue()));
        }
        if (switchInstruction instanceof JmpInstruction) {
            copyInstructions.set(i, new NopInstruction(0));
        }
    }

}
