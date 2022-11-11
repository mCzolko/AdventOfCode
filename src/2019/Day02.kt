package `2019`

import readInput

fun main() {

    fun part1(input: List<Int>): Int {
        return input.first()
    }

    fun part2(input: List<Int>): Int {
        return input.first()
    }

    val input = readInput("2019/Day02").first().split(",").map { it.toInt() }
    println(part1(input))
    println(part2(input))

}
