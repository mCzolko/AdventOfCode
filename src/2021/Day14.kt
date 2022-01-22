package `2021`

import readInput

fun main() {

    val input = readInput("2021/Day14")
    val template = input.first()
    val instructions = mutableMapOf<String, String>()
    input.takeLast(input.size - 2).forEach {
        val (pair, insert) = it.split(" -> ")
        instructions.set(pair, insert)
    }


    class Polymer(val key: String, var count: Long)


    fun countPolymers(list: List<Polymer>): Long {
        val polymerCountMap = mutableMapOf<Char, Long>()

        list.forEach {
            polymerCountMap[it.key[1]] = polymerCountMap.getOrDefault(it.key[1], 0).plus(it.count)
        }

        val firstLetter = list.first().key[0]
        polymerCountMap[firstLetter] = polymerCountMap.getOrDefault(firstLetter, 0).plus(list.first().count)

        return polymerCountMap.values.maxOf { it } - polymerCountMap.values.minOf { it }
    }


    fun polymerFission(polymerList: List<Polymer>): List<Polymer> {
        val fissionList = mutableListOf<Polymer>()

        polymerList.forEach { polymer ->
            val fissionKeyOne = polymer.key[0] + instructions.get(polymer.key)!!
            val fissionKeyTwo = instructions.get(polymer.key) + polymer.key[1]

            fissionList.add(Polymer(fissionKeyOne, polymer.count))
            fissionList.add(Polymer(fissionKeyTwo, polymer.count))
        }

        // memory optimization
        val reducedFissionList = mutableListOf<Polymer>()
        fissionList.map { it.key }.distinct().forEach {
            val count = fissionList.filter { polymer -> it == polymer.key }.sumOf { it.count }
            reducedFissionList.add(Polymer(it, count))
        }

        return reducedFissionList
    }


    fun fission(count: Int): List<Polymer> {
        var polymerList = template.windowed(2).map { Polymer(it, 1) }

        repeat(count) {
            polymerList = polymerFission(polymerList)
        }

        return polymerList
    }


    fun part1(): Long {
        return countPolymers(fission(10))
    }


    fun part2(): Long {
        return countPolymers(fission(40))
    }

    println(part1())
    println(part2())
}
