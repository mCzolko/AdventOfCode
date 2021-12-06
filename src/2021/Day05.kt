import kotlin.math.abs
import kotlin.math.absoluteValue

fun main() {

    class Point(val x: Int, val y: Int) {
        override fun toString(): String {
            return "${x}-${y}"
        }
    }

    class Direction(val from: Point, val to: Point) {
        override fun toString(): String {
            return listOf(from.toString(), to.toString()).joinToString(" -> ")
        }

        fun isHorizontal(): Boolean { return from.x == to.x }
        fun isVertical(): Boolean { return from.y == to.y }
        fun isHorizontalOrVertical(): Boolean { return isHorizontal() || isVertical() }

        fun linePoints(): List<Point> {
            if (isHorizontal()) {
                return List((from.y - to.y).absoluteValue.plus(1)) { Point(from.x, (if (from.y < to.y) from.y else to.y).plus(it)) }
            }
            if (isVertical()) {
                return List((from.x - to.x).absoluteValue.plus(1)) { Point((if (from.x < to.x) from.x else to.x).plus(it), to.y) }
            }

            val steps = abs(from.x - to.x)
            val xUp = if (from.x < to.x) 1 else -1
            val yUp = if (from.y < to.y) 1 else -1

            return (0..steps).map { step ->
                Point(from.x + step * xUp, from.y + step * yUp)
            }
        }
    }

    fun countIntersections(lines: List<Point>, intersectionCount: Int): Int {
        return lines
            .map { it.toString() }
            .groupBy { it }
            .values
            .filter { it.size >= intersectionCount }
            .size
    }

    fun part1(input: List<Direction>): Int {
        val linePoints = mutableListOf<Point>()

        val directions = input.filter { it.isHorizontalOrVertical() }
        directions.forEach { linePoints.addAll(it.linePoints()) }

        return countIntersections(linePoints, 2)
    }

    fun part2(input: List<Direction>): Int {
        val linePoints = mutableListOf<Point>()

        input.forEach { linePoints.addAll(it.linePoints()) }

        return countIntersections(linePoints, 2)
    }

    val input = readInput("2021/Day05")
        .map { it.split(" -> ") }

    val directions = input.map {
        val from = it.first().split(',')
        val to = it.last().split(',')
        Direction(
            Point(from.first().toInt(), from.last().toInt()),
            Point(to.first().toInt(), to.last().toInt()),
        )
    }

    println(part1(directions))
    println(part2(directions))

}
