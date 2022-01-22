package `2021`

import readInput

fun main() {

    val input = readInput("2021/Day13")
    val positionsInput = mutableListOf<Pair<Int, Int>>()
    val instructionsInput = mutableListOf<Pair<String, Int>>()

    var positionParse = true
    for (line in input) {
        if (line == "") {
            positionParse = false
            continue
        }

        if (positionParse) {
            val (x, y) = line.split(',').map { it.toInt() }
            positionsInput.add(Pair(x, y))
        } else {
            val (direction, distance) = line.split(' ').last().split('=')
            instructionsInput.add(Pair(direction, distance.toInt()))
        }
    }


    fun renderFold(list: List<Pair<Int, Int>>, width: Int, height: Int) {
        for (h in 0 .. height) {
            for (w in 0 .. width) {
                val point = list.find { it.first == w && it.second == h }
                if (point != null) {
                    print('#')
                } else {
                    print(' ')
                }
            }
            println()
        }
        println("======================================")
    }


    fun play() {
        val foldCount = mutableListOf<Int>()
        var width = positionsInput.maxOf { it.first } - 1
        var height = positionsInput.maxOf { it.second } - 1
        var positions = positionsInput.toList()

        for (instruction in instructionsInput) {
            // println("Instruction ${instruction}")
            // renderFold(positions, width, height)
            if (instruction.first == "x") {
                width = instruction.second
                positions = positions.map {
                    if (it.first >= instruction.second) {
                        return@map Pair(it.first - 2 * (it.first - instruction.second), it.second)
                    }
                    return@map it
                }
            } else {
                height = instruction.second
                positions = positions.map {
                    if (it.second >= instruction.second) {
                        return@map Pair(it.first, it.second - 2 * (it.second - instruction.second))
                    }
                    return@map it
                }
            }

            foldCount.add(positions.distinct().size)
        }

        renderFold(positions, width, height)

        println(foldCount.first())
    }


    play()

}
