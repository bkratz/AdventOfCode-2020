package de.birgitkratz.aoc2020.day03

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.io.File
import java.nio.charset.StandardCharsets

class Day03Test {

    @Test
    fun `process sample 1`() {
        val world = """
            ..##.......
            #...#...#..
            .#....#..#.
            ..#.#...#.#
            .#...##..#.
            ..#.##.....
            .#.#.#....#
            .#........#
            #.##...#...
            #...##....#
            .#..#...#.#
        """.trimIndent()
        val result = processWorld1(world.lines())

        result shouldBe 7
    }

    @Test
    fun `process input 1`() {
        val result = File("./input/day03.txt")
            .useLines(StandardCharsets.UTF_8) { processWorld1(it.toList()) }

        result shouldBe 216
    }

    @Test
    fun `process sample 2`() {
        val world = """
            ..##.......
            #...#...#..
            .#....#..#.
            ..#.#...#.#
            .#...##..#.
            ..#.##.....
            .#.#.#....#
            .#........#
            #.##...#...
            #...##....#
            .#..#...#.#
        """.trimIndent()
        val result = processWorld2(world.lines())

        result shouldBe 336
    }

    @Test
    fun `process input 2`() {
        val result = File("./input/day03.txt")
            .useLines(StandardCharsets.UTF_8) { processWorld2(it.toList()) }

        result shouldBe 6708199680L
    }

    @Test
    fun `process slope 1 1`() {
        val result = File("./input/day03.txt")
            .useLines(StandardCharsets.UTF_8) { treeCount(it.toList(), Slope(1, 1)) }

        result shouldBe 79
    }

    @Test
    fun `process slope 1 3`() {
        val result = File("./input/day03.txt")
            .useLines(StandardCharsets.UTF_8) { treeCount(it.toList(), Slope(1, 3)) }

        result shouldBe 216
    }

    @Test
    fun `process slope 1 5`() {
        val result = File("./input/day03.txt")
            .useLines(StandardCharsets.UTF_8) { treeCount(it.toList(), Slope(1, 5)) }

        result shouldBe 91
    }

    @Test
    fun `process slope 1 7`() {
        val result = File("./input/day03.txt")
            .useLines(StandardCharsets.UTF_8) { treeCount(it.toList(), Slope(1, 7)) }

        result shouldBe 96
    }

    @Test
    fun `process slope 2 1`() {
        val result = File("./input/day03.txt")
            .useLines(StandardCharsets.UTF_8) { treeCount(it.toList(), Slope(2, 1)) }

        result shouldBe 45
    }

    @Test
    fun `compute path`() {
        val path = path(Slope(1, 3), 5) // for a world with 6 rows
        path shouldBe listOf(Pos(0, 0), Pos(1, 3), Pos(2, 6), Pos(3, 9), Pos(4, 12), Pos(5, 15))
    }

    @Test
    fun `tree count`() {
        val world = """
            ..##.......
            #...#...#..
            .#....#..#.
            ..#.#...#.#
            .#...##..#.
            ..#.##.....
            .#.#.#....#
            .#........#
            #.##...#...
            #...##....#
            .#..#...#.#
        """.trimIndent()

        val counts = listOf(Slope(1, 1), Slope(1, 3), Slope(1, 5), Slope(1, 7), Slope(2, 1))
            .map { treeCount(world.lines(), it) }

        counts shouldBe listOf(2, 7, 3, 4, 2)
    }
}