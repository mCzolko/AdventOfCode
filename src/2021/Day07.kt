import kotlin.math.abs


fun main() {

    fun part1(input: List<Int>): Int {
        val med = median(input.map { it.toLong() })
        return input.map { abs(it - med.toInt()) }.sum()
    }

    fun part2(input: List<Int>): Int {
        val resultList = mutableListOf<Int>()

        for (i in input.minOrNull()!!..input.maxOrNull()!!) {
            resultList.add(input
                .map { abs(i - it) }
                .sumOf { it * (it + 1) / 2 }
            )
        }

        return resultList.minOrNull() ?: 0
    }

    val input = readInput("2021/Day07")
        .first()
        .split(',')
        .map { it.toInt() }
    println(part1(input))
    println(part2(input))

}
