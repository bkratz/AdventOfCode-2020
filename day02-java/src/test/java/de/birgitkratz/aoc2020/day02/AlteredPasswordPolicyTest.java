package de.birgitkratz.aoc2020.day02;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class AlteredPasswordPolicyTest {
    @Test
    void shouldParseInputIntoPasswordPolicy() {
        String policyDefinitionString = "1-3 a";
        final AlteredPasswordPolicy passwordPolicy = new AlteredPasswordPolicy(policyDefinitionString);

        assertThat(passwordPolicy)
                .hasFieldOrPropertyWithValue("firstPosition", 1)
                .hasFieldOrPropertyWithValue("secondPosition", 3)
                .hasFieldOrPropertyWithValue("passwordLetter", 'a');
    }

    @ParameterizedTest
    @MethodSource("provider")
    void shouldApplyPasswordPolicyToPassword(String policyDefinitionString, String password, boolean result) {
        assertThat(new AlteredPasswordPolicy(policyDefinitionString).apply(password)).isEqualTo(result);
    }

    static Stream<Arguments> provider() {
        return Stream.of(
                arguments("2-9 c", "ccccccccc", false),
                arguments("1-3 a", "abcde", true),
                arguments("1-3 b", "cdefg", false)
        );
    }

}