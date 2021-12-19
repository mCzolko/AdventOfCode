fun main() {

    val input = readInput("2021/Day14")
    val template = input.first()
    val instructions = mutableMapOf<String, String>()
    input.takeLast(input.size - 2).forEach {
        val (pair, insert) = it.split(" -> ")
        instructions.set(pair, insert)
    }


    fun polymeeeree(count: Int): String {
        var polymer = template

        for (i in 0 until count) {
            val windowed = polymer.windowed(2)
            polymer = windowed.mapIndexed { index, s ->
                arrayOf(
                    s[0],
                    instructions.get(s),
                    if (index == windowed.size - 1) s[1] else ""
                ).joinToString("")
            }.joinToString("")

            // println("After step ${i + 1}: ${polymer}")
        }

        return polymer
    }


    fun countPolymere(line: String): Int {
        val map = mutableMapOf<String, Int>()
        val windowed = line.windowed(1)
        val charList = windowed.distinct()

        for (char in charList) {
            map.set(char, windowed.filter { it == char }.size)
        }

        val values = map.values

        return values.maxOf { it } - values.minOf { it }
    }


    fun part1(): Int {
        return countPolymere(polymeeeree(10))
    }


    fun part2(): Int {
        return 0
    }

    println(part1())
    println(part2())

}
