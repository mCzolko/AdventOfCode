import kotlin.math.abs


fun main() {

    fun median(input: IntArray): Int {
        input.sort()
        val middle: Int = input.size / 2

        if (input.size % 2 == 1)
            return input[middle]
        else
            return (input[middle - 1] + input[middle]) / 2
    }

    fun part1(input: List<Int>): Int {
        val med = median(input.toIntArray())
        return input.map { abs(it - med) }.sum()
    }

    fun part2(input: List<Int>): Int {
        return 0
    }

    val input = readInput("2021/Day07")
        .first()
        .split(',')
        .map { it.toInt() }
    println(part1(input))
    println(part2(input))

}
