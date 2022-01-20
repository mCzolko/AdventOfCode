fun main() {

    val input = readInput("2016/Day03").map {
        it
            .windowed(5, 5)
            .map {
                it.trim().toInt()
            }
    }


    fun isTriangle(sides: List<Int>): Boolean {
        val sidesSorted = sides.sortedDescending()
        return sidesSorted.first() < (sidesSorted[1] + sidesSorted[2])
    }


    fun partOne(): Int {
        return input.count {
            isTriangle(it)
        }
    }


    fun partTwo(): Int {
        return input
            .windowed(3, 3)
            .map {
                listOf(
                    listOf(it[0][0], it[1][0], it[2][0]),
                    listOf(it[0][1], it[1][1], it[2][1]),
                    listOf(it[0][2], it[1][2], it[2][2])
                ).map { sides -> isTriangle(sides) }
            }
            .flatten()
            .count { it }
    }


    println(partOne())
    println(partTwo())

}
