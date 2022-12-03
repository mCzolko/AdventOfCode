package `2022`

import readInput

val charAoCCodes = listOf(
    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
)

fun Char.toAoCCode(): Int = charAoCCodes.indexOf(this) + 1

fun main() {


    fun resolve(rucksacks: List<List<String>>) = rucksacks.sumOf {
        it.fold(it.first().toSet()) { intersected, next ->
            intersected.intersect(next.toCharArray().toSet())
        }
        .sumOf { char -> char.toAoCCode() }
    }


    fun part1(input: List<String>): Int {
        return resolve(input.map {
            it.chunked(it.length / 2)
        })
    }


    fun part2(input: List<String>): Int = resolve(input.windowed(3, 3))


    val input = readInput("2022/Day03")
    println(part1(input))
    println(part2(input))

}
