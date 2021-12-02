fun main() {

    fun part1(input: List<Pair<String, Int>>): Int {
        var x = 0
        var y = 0

        input.forEach {
            when (it.first) {
                "up"      -> y -= it.second
                "down"    -> y += it.second
                "forward" -> x += it.second
            }
        }

        return x * y
    }

    fun part2(input: List<Pair<String, Int>>): Int {
        var x = 0
        var y = 0
        var aim = 0

        input.forEach {
            when (it.first) {
                "up"      -> aim -= it.second
                "down"    -> aim += it.second
                "forward" -> {
                    x += it.second
                    y += it.second * aim
                }
            }
        }

        return x * y
    }

    val input = readInput("Day02")
        .map { it.split(' ') }
        .map { Pair(it[0], it[1].toInt()) }

    println(part1(input))
    println(part2(input))
}
