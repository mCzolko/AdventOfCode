package `2021`

import readInput

fun main() {

    fun part1(input: List<Int>): Int {
        return input.windowed(2).count { (a, b) -> a < b }
    }

    fun part2(input: List<Int>): Int {
        return input.windowed(4).count { it[0] < it[3] }
    }

    val input = readInput("2021/Day01")
        .map { it.toInt() }
    println(part1(input))
    println(part2(input))

}
