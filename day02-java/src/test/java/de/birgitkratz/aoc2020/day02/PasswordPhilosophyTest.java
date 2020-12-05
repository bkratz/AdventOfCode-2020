package de.birgitkratz.aoc2020.day02;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PasswordPhilosophyTest {

    @Test
    void shouldParseInputIntoPasswordPolicy() {
        String policyDefinitionString = "1-3 a";
        final PasswordPolicy passwordPolicy = new PasswordPolicy(policyDefinitionString);

        assertThat(passwordPolicy)
                .hasFieldOrPropertyWithValue("minOccurence", 1)
                .hasFieldOrPropertyWithValue("maxOccurence", 3)
                .hasFieldOrPropertyWithValue("passwordLetter", 'a');
    }

    @ParameterizedTest
    @MethodSource("provider")
    void shouldApplyPasswordPolicyToPassword(String policyDefinitionString, String password, boolean result) {
        assertThat(new PasswordPolicy(policyDefinitionString).apply(password)).isEqualTo(result);
    }

    static Stream<Arguments> provider() {
        return Stream.of(
                arguments("2-9 c", "ccccccccc", true),
                arguments("1-3 a", "abcde", true),
                arguments("1-3 b", "cdefg", false)
        );
    }

}