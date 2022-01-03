fun main() {

    val input = readInput("2015/Day02")
    val intList = input.map { it.split("x").map { it.toInt() } }


    fun partOne(): Int {
        val lengthList = intList.map {
            val w = it[0]
            val h = it[1]
            val l = it[2]

            listOf(
                l*w,
                w*h,
                h*l
            )
        }

        val surfaceAreaList = lengthList.map { lengths ->
            val min = lengths.minOf { it }

            lengths.sumOf { it * 2 } + min
        }

        return surfaceAreaList.sum()
    }


    fun partTwo(): Int {
        val ribbonAreaList = intList.map {
            val bow = it.reduce { sum, element -> sum * element }

            val longestSide = it.maxOf { a -> a }
            val wrapList = it.toMutableList()
            wrapList.remove(longestSide)
            val wrap = wrapList.sumOf { it * 2 }
            listOf(wrap, bow)
        }

        return ribbonAreaList.sumOf { it.first() + it.last() }
    }


    println(partOne())
    println(partTwo())

}
