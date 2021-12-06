fun main() {

    fun processDay(input: List<Int>, day: Int = 0): Int {
        if (day == 80) {
            return input.size
        }

        val zeroCount = input.count { it == 0 }
        var nextDay = input.toMutableList()

        if (zeroCount > 0) {
            nextDay = nextDay.map { if (it == 0) 7 else it }.toMutableList()
            for (addFish in 0 until zeroCount) {
                nextDay.add(9)
            }
        }


        return processDay(nextDay.map { it.minus(1) }, day.plus(1))
    }

    fun part1(input: List<Int>): Int {
        return processDay(input)
    }

    fun part2(input: List<Int>): Int {
        return 0
    }

    val input = readInput("2021/Day06")
        .first()
        .split(',')
        .map { it.toInt() }

    println(part1(input))
    println(part2(input))

}
