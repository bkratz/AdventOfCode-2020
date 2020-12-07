package de.birgitkratz.aoc2020.day06

import io.kotest.matchers.sequences.shouldContainInOrder
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class CustomCustomsTest {
    var customCustoms = CustomCustoms()
    @Test
    fun processPart1() {
        customCustoms.processPart1() shouldBe 6416
    }

    @Test
    fun processPart2() {
        customCustoms.processPart2() shouldBe 3050
    }

    @Test
    fun groupInput1() {
        val input = """
            abc
            
            a
            b
            c
            
            ab
            ac
            
            a
            a
            a
            a
            
            b
            """.trimIndent()

        val inputList = input.lineSequence()

        customCustoms.groupInput1(inputList) shouldContainInOrder  sequenceOf("abc", "abc", "abac", "aaaa", "b")
    }

    @Test
    fun groupInput2() {
        val input = """
            abc
            
            a
            b
            c
            
            ab
            ac
            
            a
            a
            a
            a
            
            b
            """.trimIndent()
        val inputList = input.lineSequence()

        customCustoms.groupInput2(inputList) shouldContainInOrder sequenceOf("abc", "", "a", "a", "b")
    }
}