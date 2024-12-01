package `2024`

import kotlin.math.abs
import readInput
import println

typealias NumberPairs = Pair<List<Int>, List<Int>>

fun main() {

    /**
     * Helper functions
     */

    fun String.toPair(): Pair<Int, Int> {
        val (a, b) = this.split("   ")
        return Pair(a.toInt(), b.toInt())
    }

    fun Pair<Int, Int>.distance(): Int {
        return abs(this.second - this.first)
    }

    fun parseInput(input: String): NumberPairs {
        val numPairs = readInput(input).map { it.toPair() }
        return Pair(numPairs.map { it.first }, numPairs.map { it.second })
    }

    /**
     * Day 1: Historian Hysteria
     */

    fun part1(input: NumberPairs): Int {
        return input.first.sorted().zip(input.second.sorted()).sumOf { it.distance() }
    }

    fun part2(input: NumberPairs): Int {
        return input.first.map { Pair(it, input.second.count { r -> r == it }) }.sumOf { it.first * it.second }
    }


    /**
     * Tests
     */

    println("Test Day 1:")
    val testInput = parseInput("2024/Day01_test")
    part1(testInput).println()
    check(part1(testInput) == 11)
    part2(testInput).println()
    check(part2(testInput) == 31)

    /**
     * Show results
     */

    println("Result Day 1:")
    val input = parseInput("2024/Day01")
    part1(input).println()
    part2(input).println()
}
