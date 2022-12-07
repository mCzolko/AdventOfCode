package `2022`

import readInput

fun main() {


    fun findUnique(text: String, countOfUnique: Int): Int {
        val context = text.split("").windowed(countOfUnique, 1)
        val found = context
            .find { it.distinct().filter { it != "" }.size == countOfUnique }

        return countOfUnique + context.indexOf(found) - 1
    }

    fun findUniqueMultiple(input: List<String>, countOfUnique: Int): List<Pair<String, Int>> = input.map {
        it to findUnique(it, countOfUnique)
    }

    fun part1() {
        val input = readInput("2022/Day06")
        findUniqueMultiple(input, 4).forEach {
            println("${it.second} -> ${it.first}")
        }
    }

    fun part2() {
        val input = readInput("2022/Day06")
        findUniqueMultiple(input, 14).forEach {
            println("${it.second} -> ${it.first}")
        }
    }

    fun testPart1() {
        val input = readInput("2022/Day06_test")
        val result = findUniqueMultiple(input, 4)
        check(result[0].second == 7)
        check(result[1].second == 5)
        check(result[2].second == 6)
        check(result[3].second == 10)
        check(result[4].second == 11)
    }

    fun testPart2() {
        val input = readInput("2022/Day06_test2")
        val result = findUniqueMultiple(input, 14)
        check(result[0].second == 19)
        check(result[1].second == 23)
        check(result[2].second == 23)
        check(result[3].second == 29)
        check(result[4].second == 26)
    }

    testPart1()
    testPart2()
    part1()
    part2()

}
