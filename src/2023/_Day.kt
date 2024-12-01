package `2023`

import readInput
import println

fun main() {

    /**
     * Helper functions
     */

    fun parseInput(input: List<String>): List<String> {
        return input
    }

    /**
     * Day X: TITLE
     */

    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }


    /**
     * Tests
     */

    println("Test Day 1:")
    val testInput = parseInput(readInput("2023/Day01_test"))
    part1(testInput).println()
    check(part1(testInput) == 6)
    part2(testInput).println()
    check(part2(testInput) == 6)

    /**
     * Show results
     */

    println("Result Day 1:")
    val input = parseInput(readInput("2023/Day01"))
    part1(input).println()
    part2(input).println()
}
