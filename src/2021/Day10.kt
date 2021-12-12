fun main() {

    fun part1(input: List<String>): Int {
        val mapped = input.mapNotNull { line ->
            val queue = ArrayDeque<Char>()
            line.forEach {
                when (it) {
                    in "([{<" -> queue.addLast(it)
                    ')' -> if (queue.lastOrNull() == '(') queue.removeLast() else return@mapNotNull 3
                    ']' -> if (queue.lastOrNull() == '[') queue.removeLast() else return@mapNotNull 57
                    '}' -> if (queue.lastOrNull() == '{') queue.removeLast() else return@mapNotNull 1197
                    '>' -> if (queue.lastOrNull() == '<') queue.removeLast() else return@mapNotNull 25137
                }
            }
            return@mapNotNull null
        }

        return mapped.sum()
    }

    fun part2(): Int {
        return 0
    }

    val input = readInput("2021/Day10")

    println(part1(input))
    println(part2())

}
