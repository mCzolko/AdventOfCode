package `2022`

import readInput
import kotlin.math.abs

fun main() {

    val isTest = true

    val input = readInput("2022/Day10${if (isTest) "_test" else ""}")

    val instructions = input.map {
        when (it) {
            "noop" -> listOf(0)
            else -> listOf(0, it.split(" ")[1].toInt())
        }
    }
        .flatten()
        .windowed(2, 2).map { it[0] + it[1] }
    val cycles = mutableListOf<Int>()
    var sum = 1
    instructions.forEach { cycle ->
        sum += cycle
        cycles.add(sum)
    }


    fun part1(): Int {
        println(cycles.size)
        println(cycles.size / 20)
        println((cycles.size / 20) / 2)
        for (i in 0..cycles.size / 40) {
            println(cycles[(i * 20) + 9])
        }
        return 0
    }

    fun part2(): Int = 0

    println("Part 1: ${part1()}")
    println("Part 2: ${part2()}")

}
