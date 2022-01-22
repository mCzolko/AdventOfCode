package `2021`

import readInput

fun main() {

    val input = readInput("2021/Day09")
        .map { it.windowed(1).map { it.toInt() } }

    data class Position(val x: Int, val y: Int, val z: Int)

    val theMap: MutableList<Position> = mutableListOf()

    for (row in input.indices) {
        for (cell in 0 until input[row].size) {
            theMap.add(Position(cell, row, input[row][cell]))
        }
    }

    fun getPositionAt(x: Int, y: Int): Position? {
        if (x < 0 || x >= input.first().size || y < 0) return null
        return theMap.getOrNull(y * input.first().size + x)
    }

    fun Position.nearBy(): List<Position> {
        val above = getPositionAt(x, y - 1)
        val below = getPositionAt(x, y + 1)
        val left = getPositionAt(x - 1, y)
        val right = getPositionAt(x + 1, y)
        return listOfNotNull(above, below, left, right)
    }


    fun expandBasin(basin: Set<Position>): Set<Position> {
        val expandedBasin = basin.toMutableSet()
        for (position in basin) {
            expandedBasin.addAll(position.nearBy().filter { it.z != 9 })
        }
        return expandedBasin
    }

    fun findBasin(position: Position): Set<Position> {
        var basin = setOf(position)

        while (true) {
            val expandedBasin = expandBasin(basin)
            if (expandedBasin.size == basin.size) return expandedBasin
            basin = expandedBasin
        }
    }

    fun part1(): List<Position> {
        return theMap.filter {
            it.nearBy().all { nearByPosition ->
                nearByPosition.z > it.z
            }
        }
    }

    fun part2(): Int {
        return part1()
            .map { findBasin(it) }
            .sortedByDescending { it.size }
            .take(3)
            .map { it.size }
            .reduce { a, b -> a * b }
    }

    println(part1().map { it.z + 1}.sum())
    println(part2())

}
