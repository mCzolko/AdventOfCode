package `2022`

import readInput
import kotlin.math.abs

operator fun Pair<Int, Int>.plus(next: Pair<Int, Int>) = Pair(this.first + next.first, this.second + next.second)

fun main() {

    val isTest = false

    val input = readInput("2022/Day09${if (isTest) "_test" else ""}")
        .map { it.split(" ") }

    val move = mapOf("L" to Pair(-1, 0), "U" to Pair(0, -1), "R" to Pair(1, 0), "D" to Pair(0, 1))

    fun moveRope(moveCallback: (move: MutableList<Pair<Int, Int>>) -> Unit) {
        val rope = MutableList(10) { Pair(0, 0) }
        for ((to, moves) in input) {
            for (y in 1..moves.toInt()) {
                rope[0] = rope[0] + move[to]!!
                for (i in 0..rope.size - 2) {
                    val dx = rope[i].first - rope[i + 1].first
                    val dy = rope[i].second - rope[i + 1].second
                    if (abs(dx) >= 2 || abs(dy) >= 2) {
                        rope[i + 1] = rope[i + 1] + Pair(dx.coerceIn(-1..1), dy.coerceIn(-1..1))
                    }
                }
                moveCallback(rope)
            }
        }
    }

    fun part1(): Int = buildSet {
        moveRope {
            add(it[1])
        }
    }.size

    fun part2(): Int = buildSet {
        moveRope {
            add(it.last())
        }
    }.size

    println("Part 1: ${part1()}")
    println("Part 2: ${part2()}")

}
