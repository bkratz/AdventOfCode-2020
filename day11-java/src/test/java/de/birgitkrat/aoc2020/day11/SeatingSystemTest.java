package de.birgitkrat.aoc2020.day11;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SeatingSystemTest {

    SeatingSystem seatingSystem = new SeatingSystem();

    @Test
    void processPart1() throws IOException {
        assertThat(seatingSystem.processPart1()).isEqualTo(2310L);
    }

    @Test
    void processPart2() throws IOException {
        assertThat(seatingSystem.processPart2()).isEqualTo(2074L);
    }

    @Test
    void processInputPart1() {
        String[] input = new String[] {
                "L.LL.LL.LL",
                "LLLLLLL.LL",
                "L.L.L..L..",
                "LLLL.LL.LL",
                "L.LL.LL.LL",
                "L.LLLLL.LL",
                "..L.L.....",
                "LLLLLLLLLL",
                "L.LLLLLL.L",
                "L.LLLLL.LL"
        };

        assertThat(seatingSystem.processInputPart1(Arrays.asList(input))).isEqualTo(37L);
    }

    @Test
    void processInputPart2() {
        String[] input = new String[] {
                "L.LL.LL.LL",
                "LLLLLLL.LL",
                "L.L.L..L..",
                "LLLL.LL.LL",
                "L.LL.LL.LL",
                "L.LLLLL.LL",
                "..L.L.....",
                "LLLLLLLLLL",
                "L.LLLLLL.L",
                "L.LLLLL.LL"
        };

        assertThat(seatingSystem.processInputPart2(Arrays.asList(input))).isEqualTo(26L);
    }

    @Test
    void testInputFirstGenerationPart1() {
        String[] input = new String[] {
                "L.LL.LL.LL",
                "LLLLLLL.LL",
                "L.L.L..L..",
                "LLLL.LL.LL",
                "L.LL.LL.LL",
                "L.LLLLL.LL",
                "..L.L.....",
                "LLLLLLLLLL",
                "L.LLLLLL.L",
                "L.LLLLL.LL"
        };

        String[] firstGeneration = new String[] {
                "#.##.##.##",
                "#######.##",
                "#.#.#..#..",
                "####.##.##",
                "#.##.##.##",
                "#.#####.##",
                "..#.#.....",
                "##########",
                "#.######.#",
                "#.#####.##"
        };

        assertThat(seatingSystem.calculateNextGenerationPart1(String.join("", input), 10, 10))
                .isEqualTo(String.join("", firstGeneration));
    }

    @Test
    void testInputFirstGenerationPart2() {
        String[] input = new String[] {
                "L.LL.LL.LL",
                "LLLLLLL.LL",
                "L.L.L..L..",
                "LLLL.LL.LL",
                "L.LL.LL.LL",
                "L.LLLLL.LL",
                "..L.L.....",
                "LLLLLLLLLL",
                "L.LLLLLL.L",
                "L.LLLLL.LL"
        };

        String[] firstGeneration = new String[] {
                "#.##.##.##",
                "#######.##",
                "#.#.#..#..",
                "####.##.##",
                "#.##.##.##",
                "#.#####.##",
                "..#.#.....",
                "##########",
                "#.######.#",
                "#.#####.##"
        };

        assertThat(seatingSystem.calculateNextGenerationPart2(String.join("", input), 10, 10))
                .isEqualTo(String.join("", firstGeneration));
    }

    @Test
    void testnextGenerationPart2() {
        String[] firstGeneration = new String[] {
                "#.##.##.##",
                "#######.##",
                "#.#.#..#..",
                "####.##.##",
                "#.##.##.##",
                "#.#####.##",
                "..#.#.....",
                "##########",
                "#.######.#",
                "#.#####.##"
        };

        String[] nextGeneration = new String[] {
                "#.LL.LL.L#",
                "#LLLLLL.LL",
                "L.L.L..L..",
                "LLLL.LL.LL",
                "L.LL.LL.LL",
                "L.LLLLL.LL",
                "..L.L.....",
                "LLLLLLLLL#",
                "#.LLLLLL.L",
                "#.LLLLL.L#"
        };
        assertThat(seatingSystem.calculateNextGenerationPart2(String.join("", firstGeneration), 10, 10))
                .isEqualTo(String.join("", nextGeneration));

    }

    @Test
    void findAdjacentSeatIndicesPart1() {
        List<Integer> indices = seatingSystem.findAdjacentSeatIndicesPart1(11, 3, 10);
        assertThat(indices).containsExactly(0, 1, 2, 10, 12, 20, 21, 22);

        indices = seatingSystem.findAdjacentSeatIndicesPart1(10, 3, 10);
        assertThat(indices).containsExactly(0, 1, 11, 20, 21);

        indices = seatingSystem.findAdjacentSeatIndicesPart1(19, 3, 10);
        assertThat(indices).containsExactly(8, 9, 18, 28, 29);

        indices = seatingSystem.findAdjacentSeatIndicesPart1(0, 3, 10);
        assertThat(indices).containsExactly(1, 10, 11);

        indices = seatingSystem.findAdjacentSeatIndicesPart1(29, 3, 10);
        assertThat(indices).containsExactly(18, 19, 28);
    }

    @Test
    void findAdjacentSeatIndicesPart2() {
        List<List<Integer>> indices = seatingSystem.findAdjacentSeatIndicesPart2(39, 9, 9);

        assertThat(indices).hasSize(8);
        assertThat(indices.get(0)).containsExactly(38, 37, 36);
        assertThat(indices.get(1)).containsExactly(40, 41, 42, 43, 44);
        assertThat(indices.get(2)).containsExactly(30, 21, 12, 3);
        assertThat(indices.get(3)).containsExactly(48, 57, 66, 75);
        assertThat(indices.get(4)).containsExactly(29, 19, 9);
        assertThat(indices.get(5)).containsExactly(49, 59, 69, 79);
        assertThat(indices.get(6)).containsExactly(31, 23, 15, 7);
        assertThat(indices.get(7)).containsExactly(47, 55, 63);
    }

    @Test
    void becomesOccupied() {
        String input = "L.LL.LL.LLLLLLLLL.LLL.L.L..L..";
        assertThat(seatingSystem.calculateNextSeatGenerationPart1(input, 11, 3, 10)).isEqualTo('#');
        assertThat(seatingSystem.calculateNextSeatGenerationPart1(input, 1, 3, 10)).isEqualTo('.');
    }

    @Test
    void becomesFree() {
        String input = "#.##.##.##"
                     + "#######.##"
                     + "#.#.#..#..";
        assertThat(seatingSystem.calculateNextSeatGenerationPart1(input, 0, 3, 10)).isEqualTo('#');
        assertThat(seatingSystem.calculateNextSeatGenerationPart1(input, 1, 3, 10)).isEqualTo('.');
        assertThat(seatingSystem.calculateNextSeatGenerationPart1(input, 2, 3, 10)).isEqualTo('L');
    }
}