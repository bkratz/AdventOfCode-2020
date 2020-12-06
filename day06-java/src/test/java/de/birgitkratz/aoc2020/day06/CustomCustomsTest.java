package de.birgitkratz.aoc2020.day06;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

class CustomCustomsTest {

    CustomCustoms customCustoms = new CustomCustoms();

    @Test
    void processPart1() throws IOException {
        assertThat(customCustoms.processPart1()).isEqualTo(6416);
    }

    @Test
    void processPart2() throws IOException {
        assertThat(customCustoms.processPart2()).isEqualTo(3050);
    }

    @Test
    void groupInput1() {
        String input = "abc\n"
                + "\n"
                + "a\n"
                + "b\n"
                + "c\n"
                + "\n"
                + "ab\n"
                + "ac\n"
                + "\n"
                + "a\n"
                + "a\n"
                + "a\n"
                + "a\n"
                + "\n"
                + "b";

        final List<String> inputList = input.lines().collect(toList());
        assertThat(customCustoms.groupInput1(inputList)).contains("abc", "abc", "abac", "aaaa", "b");
    }

    @Test
    void groupInput2() {
        String input = "abc\n"
                + "\n"
                + "a\n"
                + "b\n"
                + "c\n"
                + "\n"
                + "ab\n"
                + "ac\n"
                + "\n"
                + "a\n"
                + "a\n"
                + "a\n"
                + "a\n"
                + "\n"
                + "b";

        final List<String> inputList = input.lines().collect(toList());
        assertThat(customCustoms.groupInput2(inputList)).contains("abc", "", "a", "a", "b");
    }
}