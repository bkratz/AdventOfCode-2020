package de.birgitkratz.aoc2020.day08;

import java.util.Objects;

public class NopInstruction implements Instruction {

    private int value;

    public NopInstruction(final int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public int processIndex(int index) {
        return ++index;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final NopInstruction that = (NopInstruction) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
