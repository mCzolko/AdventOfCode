package `2021`

import readInput

fun main() {

    fun processDay(fishes: Map<Long, Long>): Map<Long, Long> {
        val nextGen = fishes.mapKeys { (key, _) -> key - 1 }.toMutableMap()
        nextGen[8] = nextGen.getOrDefault(-1, 0)
        nextGen[6] = nextGen.getOrDefault(6, 0) + nextGen.getOrDefault(-1, 0)
        nextGen.remove(-1)
        return nextGen
    }

    fun processGeneration(fishGenerations: List<Long>, days: Int): Long {
        val mapTimerToCount = fishGenerations
            .groupBy { it }
            .mapValues { it.value.size.toLong() }

        var processed = processDay(mapTimerToCount)

        repeat (days - 1) {
            processed = processDay(processed)
        }

        return processed.values.reduce { acc, i -> acc + i }
    }

    fun part1(input: List<Long>): Long {
        return processGeneration(input, 80)
    }

    fun part2(input: List<Long>): Long {
        return processGeneration(input, 256)
    }

    val input = readInput("2021/Day06")
        .first()
        .split(',')
        .map { it.toLong() }

    println(part1(input))
    println(part2(input))

}
