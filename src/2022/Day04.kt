package `2022`

import readInput


private infix fun IntRange.contains(other: IntRange): Boolean =
    this.first <= other.first && this.last >= other.last


fun main() {

    fun part1(input: List<Pair<IntRange, IntRange>>): Int = input.map {
        val (a, b) = it
        b contains a || a contains b
    }.count { it }


    fun part2(input: List<Pair<IntRange, IntRange>>): Int = input.map {
        val (a, b) = it
        a intersect b
    }.count { it.isNotEmpty() }


    val input = readInput("2022/Day04").map {
        it.split(",").map {
            val range = it.split("-").map { it.toInt() }
            range.first().toString().toInt()..range.last().toString().toInt()
        }
    }.map {
        Pair(it.first(), it.last())
    }

    println(part1(input))
    println(part2(input))

}
