package `2019`

import readInput

fun main() {

    fun part1(input: List<Int>): Int {
        return input.sumOf { it.floorDiv(3).minus(2) }
    }

    fun fuel(mass: Int): Int {
        val fuel = mass.floorDiv(3).minus(2)
        return if (fuel > 0) fuel + fuel(fuel) else 0
    }

    fun part2(input: List<Int>): Int {
        return input.sumOf { fuel(it) }
    }

    val input = readInput("2019/Day01")
        .map { it.toInt() }
    println(part1(input))
    println(part2(input))

}
