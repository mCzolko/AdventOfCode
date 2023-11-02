package `2022`

import readInput

fun main() {

    val isTest = false

    val input = readInput("2022/Day08${if (isTest) "_test" else ""}")
        .map { it.windowed(1)
        .map { num -> num.toInt() } }

    data class Position(val x: Int, val y: Int, val z: Int)

    val map: MutableList<Position> = mutableListOf()

    for (row in input.indices) {
        for (cell in 0 until input[row].size) {
            map.add(Position(cell, row, input[row][cell]))
        }
    }

    fun getPositionAt(x: Int, y: Int): Position? {
        if (x < 0 || x >= input.first().size || y < 0) return null
        return map.getOrNull(y * input.first().size + x)
    }

    fun Position.isVisibleToNext(next: Position?): Boolean {
        return if (next == null) true else this.z > next.z
    }

    fun Position.visibleFromLeft(): Boolean {
        for (x in 0 until this.x) {
            if (!this.isVisibleToNext(getPositionAt(x, this.y))) return false
        }
        return true
    }

    fun Position.visibleFromRight(): Boolean {
        for (x in this.x + 1 until input.first().size) {
            if (!this.isVisibleToNext(getPositionAt(x, this.y))) return false
        }
        return true
    }

    fun Position.visibleFromTop(): Boolean {
        for (y in 0 until this.y) {
            if (!this.isVisibleToNext(getPositionAt(this.x, y))) return false
        }
        return true
    }

    fun Position.visibleFromBottom(): Boolean {
        for (y in this.y + 1 until input.size) {
            if (!this.isVisibleToNext(getPositionAt(this.x, y))) return false
        }
        return true
    }

    fun Position.isVisible(): Boolean =
        this.visibleFromLeft() || this.visibleFromRight() || this.visibleFromTop() || this.visibleFromBottom()

    fun Position.canSeeNext(next: Position): Boolean = this.z > next.z

    fun Position.visibleBottomPoints(): Int {
        var visiblePoints = 0
        for (y in this.y + 1 until input.size) {
            val p = getPositionAt(this.x, y) ?: return visiblePoints
            if (!this.canSeeNext(p)) {
                return visiblePoints + 1
            }
            visiblePoints++
        }
        return visiblePoints
    }

    fun Position.visibleTopPoints(): Int {
        var visiblePoints = 0
        for (y in this.y - 1 downTo 0) {
            val p = getPositionAt(this.x, y) ?: return visiblePoints
            if (!this.canSeeNext(p)) {
                return visiblePoints + 1
            }
            visiblePoints++
        }
        return visiblePoints
    }

    fun Position.visibleLeftPoints(): Int {
        var visiblePoints = 0
        for (x in this.x - 1 downTo 0) {
            val p = getPositionAt(x, this.y) ?: return visiblePoints
            if (!this.canSeeNext(p)) {
                return visiblePoints + 1
            }
            visiblePoints++
        }
        return visiblePoints
    }

    fun Position.visibleRightPoints(): Int {
        var visiblePoints = 0
        for (x in this.x + 1 until input.size) {
            val p = getPositionAt(x, this.y) ?: return visiblePoints
            if (!this.canSeeNext(p)) {
                return visiblePoints + 1
            }
            visiblePoints++
        }
        return visiblePoints
    }

    fun Position.visiblePoints(): List<Int> =
        listOf(this.visibleLeftPoints(), this.visibleTopPoints(), this.visibleRightPoints(), this.visibleBottomPoints())

    fun Position.visiblePointsSum(): Int = this.visiblePoints().reduce { acc, i -> acc * i }

    fun part1(): Int {
        val visibleOutsideGrid = (input.size * 4) - 4
        var visible = 0
        for (x in 1 .. input.size - 2) {
            for (y in 1 .. input.size - 2) {
                if(getPositionAt(x, y)?.isVisible() == true) {
                    visible++
                }
            }
        }
        return visible + visibleOutsideGrid
    }

    fun testPart1() {
        if (isTest) {
            check(part1() == 21)
        } else {
            check(part1() == 1676)
        }
    }

    fun part2(): Int {
        var visible = 0
        for (x in 1 .. input.size) {
            for (y in 1 .. input.size) {
                val point = getPositionAt(x, y)
                val pointsCount = point?.visiblePointsSum()
                if (pointsCount != null) {
                    if (pointsCount > visible) {
                        visible = pointsCount
                    }
                }
            }
        }
        return visible
    }

    fun testPart2() {
        if (isTest) {
            val testedPosition = getPositionAt(2, 3)
            check(testedPosition?.visiblePointsSum() == 8) {
                "Expected 8, got ${testedPosition?.visiblePointsSum()}"
            }
            check(part2() == 8) {
                "Expected 8, got ${part2()}"
            }
        } else {
            check(part2() == 313200)
        }
    }

    println("Part 1: ${part1()}")
    testPart1()
    println("Part 2: ${part2()}")
    testPart2()

}
