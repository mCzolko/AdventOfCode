fun main() {

    val input = readInput("2016/Day02").map { it.windowed(1) }
    var x = 1
    var y = 1


    fun resolveNumber(moves: List<String>): Int {
        fun opX(steps: Int) {
            if (x + steps in 0..2) {
                x += steps
            }
        }

        fun opY(steps: Int) {
            if (y + steps in 0..2) {
                y += steps
            }
        }

        moves.forEach {
            when (it) {
                "U" -> opY(-1)
                "D" -> opY(1)
                "L" -> opX(-1)
                "R" -> opX(1)
            }
        }

        return (1 + x) + (y * 3)
    }


    fun partOne(): Int {
        return input.map { resolveNumber(it) }.joinToString("").toInt()
    }


    fun partTwo(): Int {
        return 0
    }


    println(partOne())
    println(partTwo())

}
