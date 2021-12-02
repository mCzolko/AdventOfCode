fun main() {

    fun part1(input: List<Int>): Int {
        var prevNum = 0
        var i = 0

        input.forEach {
            if (prevNum != 0) {
                if (it > prevNum) i++
            }
            prevNum = it
        }

        return i
    }

    fun part2(input: List<Int>): Int {
        var prevNum = 0
        var i = 0

        input.forEachIndexed { index, it ->
            if (index > input.size - 3) return i

            val num = it + input[index + 1 ] + input[index + 2]

            if (prevNum != 0) {
                if (num > prevNum) i++
            }
            prevNum = num
        }

        return i
    }

    val input = readInput("Day01")
        .map { it.toInt() }
    println(part1(input))
    println(part2(input))
}
