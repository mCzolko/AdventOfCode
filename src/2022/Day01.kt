package `2022`

import readInput

fun main() {

    fun elfCalorieSum(input: List<String>): List<Int> {
        return input
            .joinToString("|")
            .split("||")
            .map {
                it.split("|").sumOf { num -> num.toInt() }
            }
    }

    fun part1(input: List<String>): Int {
        return elfCalorieSum(input).max()
    }

    fun part2(input: List<String>): Int {
        return elfCalorieSum(input).sortedDescending().take(3).sum()
    }

    val input = readInput("2022/Day01")
    println(part1(input))
    println(part2(input))

}
