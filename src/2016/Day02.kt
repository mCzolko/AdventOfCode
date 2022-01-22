package `2016`

import readInput

fun main() {

    val input = readInput("2016/Day02").map { it.windowed(1) }


    fun resolveNumber(moves: List<String>, action: (axis: Char, steps: Int) -> Unit?) {
        moves.forEach {
            when (it) {
                "U" -> action('Y', -1)
                "D" -> action('Y', 1)
                "L" -> action('X', -1)
                "R" -> action('X', 1)
            }
        }
    }


    fun partOne(): Int {
        var x = 1
        var y = 1

        fun op(axis: Int, steps: Int): Int {
            return if (axis + steps in 0..2) steps else 0
        }

        val numberList = input.map {
            resolveNumber(it) { axis, steps ->
                when (axis) {
                    'X' -> x += op(x, steps)
                    'Y' -> y += op(y, steps)
                }
            }

            (1 + x) + (y * 3)
        }

        return numberList.joinToString("").toInt()
    }


    fun partTwo(): String {
        val keyboard = listOf(
            listOf(null, null, '1', null, null),
            listOf(null, '2', '3', '4', null),
            listOf('5', '6', '7', '8', '9'),
            listOf(null, 'A', 'B', 'C', null),
            listOf(null, null, 'D', null, null)
        )

        var x = 0
        var y = 2

        val numberList = input.map {
            resolveNumber(it) { axis, steps ->
                when (axis) {
                    'X' -> {
                        val canMove = x + steps in 0..4

                        if (canMove && keyboard[y][x + steps] != null) {
                            x += steps
                        }
                    }
                    'Y' -> {
                        val canMove = y + steps in 0..4

                        if (canMove && keyboard[y + steps][x] != null) {
                            y += steps
                        }
                    }
                }
            }

            keyboard[y][x]
        }

        return numberList.joinToString("")
    }


    println(partOne())
    println(partTwo())

}
