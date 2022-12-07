package `2022`

import readInput


data class Movement(val move: Int, val from: Int, val to: Int)


fun MutableList<Char>.pullLast(count: Int): List<Char> {
    val last = this.takeLast(count)
    for (i in 0 until count) {
        this.removeLast()
    }
    return last
}


fun main() {

    val input = readInput("2022/Day05")
    val input2 = input.joinToString("||")
    val (stacksRaw, instructionsRaw) = input2.split("||||").map { it.split("||") }

    val stacksHorizontal = stacksRaw
        .slice(0..stacksRaw.size - 2)
        .map { it.replace("    ", "-") }
        .map { it.replace(" ", "") }
        .map { it.replace("[", "") }
        .map { it.replace("]", "") }


    val stacks = mutableMapOf<Int, MutableList<Char>>()
    stacksHorizontal.forEach { s ->
        s.windowed(1).forEachIndexed { i, c ->
            if (stacks[i] == null) {
                stacks[i] = mutableListOf()
            }

            if (c != "-") {
                stacks[i]!!.add(0, c.toCharArray()[0])
            }
        }
    }

    val instructions = instructionsRaw.map {
        val split = it.split(" ")
        Movement(split[1].toInt(), split[3].toInt() - 1, split[5].toInt() - 1)
    }


    val stackOne = stacks.toMutableMap()
    val stackTwo = stacks.toMutableMap()


    fun part1(): String {
        val stack = stackOne
        instructions.forEach {
            val (move, from, to) = it
            val taken = stack[from]!!.pullLast(move)
            stack[to]!!.addAll(taken.reversed())
        }

        return stack.values.joinToString("") {
            if (it.lastOrNull() == null) "" else it.last().toString()
        }
    }


    fun part2(): String {
        val stack = stackTwo
        instructions.forEach {
            val (move, from, to) = it
            val taken = stack[from]!!.pullLast(move)
            stack[to]!!.addAll(taken)
        }

        return stack.values.joinToString("") {
            if (it.lastOrNull() == null) "" else it.last().toString()
        }
    }


    println(part1())
    println(part2())

}
