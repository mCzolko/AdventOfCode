package `2023`

import readInput
import println

data class Color(val color: String, val count: Int)

typealias GameSet = List<Color>
typealias GameSets = List<GameSet>
typealias Game = Pair<Int, GameSets>

fun main() {

    /**
     * Helper functions
     */

    fun parseRow(row: String): GameSets {
        return row
            .split(':')
            .last()
            .trim()
            .split(';')
            .map { sets ->
                sets
                    .trim()
                    .split(',')
                    .map { it.trim() }
            }.map { sets ->
                sets.map {
                    val (count, color, _) = it.trim().split(' ')
                    Color(color, count.toInt())
                }
            }
    }

    fun parseInput(input: List<String>): List<Game> {
        return input.mapIndexed { i, row ->
            Pair(i + 1, parseRow(row))
        }
    }

    fun List<Int>.multiply(): Int {
        var last = this.first()
        this.takeLast(this.size - 1).forEach {
            last *= it
        }
        return last
    }

    /**
     * Day 2: Cube Conundrum
     */

    fun part1(input: List<Game>): Int {
        return input.filter { games ->
            for (set in games.second) {
                set.forEach {
                    if (it.color == "red" && it.count > 12 || it.color == "green" && it.count > 13 || it.color == "blue" && it.count > 14) {
                        return@filter false
                    }
                }
            }
            return@filter true
        }.sumOf { it.first }
    }

    fun part2(input: List<Game>): Int {
        return input
            .map { it.second }
            .sumOf { gameSets ->
                gameSets.flatten()
                    .groupBy { it.color }
                    .map {
                        it.value.maxOf { color -> color.count }
                    }
                    .multiply()
            }
    }


    /**
     * Tests
     */

    println("Test Day 2:")
    val testInput = parseInput(readInput("2023/Day02_test"))
    part1(testInput).println()
    check(part1(testInput) == 8)
    part2(testInput).println()
    check(part2(testInput) == 2286)

    /**
     * Show results
     */

    println("Result Day 2:")
    val input = parseInput(readInput("2023/Day02"))
    part1(input).println()
    part2(input).println()
}
