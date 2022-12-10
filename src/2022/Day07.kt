package `2022`

import readInput

data class Folder(val path: String, var parent: Folder? = null) {
    val children: MutableList<Folder> = mutableListOf()
    var fileSize: Int = 0
}

fun main() {

    fun folderSizeStack(input: List<String>): List<Int> {
        val root = Folder("/")
        var current = root

        val commands = input
            .map { it.replace("$ ", "") }
            .filter { it != "ls" }

        for (line in commands) {
            val (command, arg) = line.split(" ")
            when (command) {
                "cd" -> current = when (arg) {
                    "/" -> root
                    ".." -> current.parent!!
                    else -> current.children.filter { it.path == arg }.first()
                }
                "dir" -> current.children.add(Folder(arg, current))
                else -> current.fileSize += command.toInt()
            }
        }

        return buildList {
            fun countFileSizes(folder: Folder): Int {
                add(folder.fileSize + folder.children.sumOf(::countFileSizes) )
                return this.last()
            }
            countFileSizes(root)
        }
    }

    fun part1(input: List<String>): Int {
        val stack = folderSizeStack(input)
        return stack.filter { it <= 100000 }.sum()
    }

    fun part2(input: List<String>): Int {
        val stack = folderSizeStack(input)
        val filter = 30000000 - (70000000 - stack.max())
        return stack.filter { it >= filter }.min()
    }

    val input = readInput("2022/Day07")

    check(part1(input) == 1555642)
    check(part2(input) == 5974547)

    println(part1(input))
    println(part2(input))

}
