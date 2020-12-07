package de.birgitkratz.aoc2020.day06

import java.io.File
import java.nio.charset.StandardCharsets

class CustomCustoms {

    fun processPart1(): Int {
        return File("./input/input.txt").useLines(StandardCharsets.UTF_8, ::processInput1)
    }

    fun processPart2(): Int {
        return File("./input/input.txt").useLines(StandardCharsets.UTF_8, ::processInput2)
    }

    fun processInput1(lines: Sequence<String>): Int {
        return groupInput1(lines)
            .map { s: String -> s.chunked(1).distinct().joinToString("").length }
            .sum()
    }

    fun processInput2(lines: Sequence<String>): Int {
        return groupInput2(lines)
            .map { s: String -> s.length }
            .sum()
    }

    fun groupInput1(input: Sequence<String>): Sequence<String> {
        val groups = mutableListOf<String>()
        var group = ""

        input.forEach { line ->
            when {
                line.isBlank() -> {
                    groups.add(group.trim())
                    group = ""
                }
                else -> {
                    group += line.trim()
                }
            }
        }
        groups.add(group.trim())
        return groups.asSequence()
    }

    fun groupInput2(input: Sequence<String>): Sequence<String> {
        val groups: MutableList<String> = ArrayList()
        var index = 0
        var group: MutableList<String> = ArrayList()
        input.forEach { line  ->
            when {
                line.isNotBlank() -> {
                    when (index) {
                        0    -> group = line.chunked(1) as MutableList<String>
                        else -> group.retainAll(line.chunked(1))
                    }
                    index++
                }
                else -> {
                    groups.add(group.joinToString(""))
                    index = 0
                    group = ArrayList()
                }
            }
        }
        groups.add(group.joinToString(""))
        return groups.asSequence()
    }
}