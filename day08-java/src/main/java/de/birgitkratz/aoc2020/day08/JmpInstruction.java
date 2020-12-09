package de.birgitkratz.aoc2020.day08;

import java.util.Objects;

public class JmpInstruction implements Instruction {

    private int value;

    public JmpInstruction(final int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public int processIndex(int index) {
        return index + value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final JmpInstruction that = (JmpInstruction) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
