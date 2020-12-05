package de.birgitkratz.aoc2020.day04;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KeyValueValidatorTest {

    KeyValueValidator keyValueValidator = new KeyValueValidator();

    @Test
    void validateBYR() {
        assertThat(keyValueValidator.validateBYR("2002")).isTrue();
        assertThat(keyValueValidator.validateBYR("2003")).isFalse();
    }

    @Test
    void validateHGT() {
        assertThat(keyValueValidator.validateHGT("60in")).isTrue();
        assertThat(keyValueValidator.validateHGT("190cm")).isTrue();
        assertThat(keyValueValidator.validateHGT("190in")).isFalse();
        assertThat(keyValueValidator.validateHGT("190")).isFalse();
    }

    @Test
    void validateHCL() {
        assertThat(keyValueValidator.validateHCL("#123abc")).isTrue();
        assertThat(keyValueValidator.validateHCL("#123abz")).isFalse();
        assertThat(keyValueValidator.validateHCL("123abc")).isFalse();
    }

    @Test
    void validateECL() {
        assertThat(keyValueValidator.validateECL("brn")).isTrue();
        assertThat(keyValueValidator.validateECL("wat")).isFalse();
    }

    @Test
    void validatePID() {
        assertThat(keyValueValidator.validatePID("000000001")).isTrue();
        assertThat(keyValueValidator.validatePID("0123456789")).isFalse();
    }
}