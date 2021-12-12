fun main() {

    fun part1(input: List<String>): Int {
        return input
            .map { it.split(" | ").last() }
            .map { it.split(" ") }
            .flatten()
            .count { it.length in setOf(2, 3, 4, 7) }
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val input = readInput("2021/Day08")

    println(part1(input))
    println(part2(input))

}
