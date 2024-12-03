package `2024`

import readInput
import println

fun main() {

    /**
     * Helper functions
     */

    fun parseInput(input: List<String>): String {
        return input.joinToString("")
    }

    /**
     * Day 3: Mull It Over
     */

    fun part1(input: String): Int {
        val regex = Regex("""mul\(\d+,\d+\)""")

        return regex
            .findAll(input)
            .toList()
            .sumOf { match ->
                match.value
                    .takeLast(match.value.length - 4)
                    .dropLast(1)
                    .split(",")
                    .let {
                        it.first().toInt() * it.last().toInt()
                    }
            }
    }

    fun part2(input: String): Int {
        val adjustedInput = input
            .split("don't()")
            .mapIndexed { index, next ->
                if (index == 0) return@mapIndexed listOf(next)

                next.split("do()").drop(1)
            }
            .flatten()
            .joinToString("")

        return part1(adjustedInput)
    }


    /**
     * Tests
     */

    println("Test Day 3:")
    val testInput1 = parseInput(readInput("2024/Day03_test"))
    part1(testInput1).println()
    check(part1(testInput1) == 161)
    val testInput2 = parseInput(readInput("2024/Day03_test_2"))
    part2(testInput2).println()
    check(part2(testInput2) == 48)

    /**
     * Show results
     */

    println("Result Day 3:")
    val input = parseInput(readInput("2024/Day03"))
    part1(input).println()
    part2(input).println()
}
