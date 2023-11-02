package `2015`

import readInput

fun main() {

    fun mappedInput(input: List<String>): List<Int> {
        return input
            .first()
            .windowed(1)
            .map { if (it == "(") 1 else -1 }
    }

    fun part1(input: List<String>): Int {
        return mappedInput(input).sum()
    }

    fun part2(input: List<String>): Int {
        return mappedInput(input)
            .scan(0) { acc, i -> acc + i }
            .indexOfFirst { it == -1 }
    }

    val input = readInput("2015/Day01")
    println(part1(input))
    println(part2(input))

}
