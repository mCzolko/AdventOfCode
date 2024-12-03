package `2024`

import readInput
import println
import kotlin.math.abs

fun main() {

    /**
     * Helper functions
     */

    fun parseInput(input: List<String>): List<List<Int>> {
        return input.map { it.split(" ").map { it.toInt() } }
    }

    fun List<Int>.isDescendingOrAscending(): Boolean {
        return this == this.sorted() || this == this.sortedDescending()
    }

    fun List<Int>.withinStepRange(stepRange: IntRange): Boolean {
        return this.windowed(2, 1).none { abs(it[1] - it[0]) !in stepRange }
    }

    fun List<Int>.safe(): Boolean {
        return this.isDescendingOrAscending() && this.withinStepRange(1..3)
    }

    /**
     * Day 2: Red-Nosed Reports
     */

    fun part1(input: List<List<Int>>): Int {
        return input.filter { level ->
            level.safe()
        }.size
    }

    fun part2(input: List<List<Int>>): Int {
        return input.filter { level ->
            if (level.safe()) {
                return@filter true
            } else {
                level.forEachIndexed { i, _ ->
                    (level.take(i) + level.takeLast(level.size - i - 1)).safe().let {
                        if (it) {
                            return@filter true
                        }
                    }
                }
                return@filter false
            }
        }.size
    }


    /**
     * Tests
     */

    println("Test Day 1:")
    val testInput = parseInput(readInput("2024/Day02_test"))
    part1(testInput).println()
    check(part1(testInput) == 2)
    part2(testInput).println()
    check(part2(testInput) == 4)

    /**
     * Show results
     */

    println("Result Day 1:")
    val input = parseInput(readInput("2024/Day02"))
    part1(input).println()
    part2(input).println()
}
