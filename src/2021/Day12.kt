package `2021`

import readInput


interface Allow {
    fun allow(caveName: String, path: List<String>): Boolean
}


fun main() {

    fun String.isUpperCase(): Boolean = all { it.isUpperCase() }

    val paths = readInput("2021/Day12")
        .map { it.split("-") }
        .flatMap {
            listOf(
                it.first() to it.last(),
                it.last() to it.first()
            )
        }
        .groupBy({ it.first }, { it.second })




    class PartOneAllow : Allow {

        override fun allow(caveName: String, path: List<String>): Boolean {
            return caveName.isUpperCase() || caveName !in path
        }

    }


    class PartTwoAllow : Allow {

        override fun allow(caveName: String, path: List<String>): Boolean {
            return when {
                caveName.isUpperCase() -> true
                caveName == "start" -> false
                caveName !in path -> true
                else -> {
                    path
                        .filterNot { it.isUpperCase() }
                        .groupBy { it }
                        .none { it.value.size == 2 }
                }
            }
        }
    }


    class PathSearcher(a: Allow) : Allow by a {

        fun searchPaths(path: List<String> = listOf("start")): List<List<String>> {
            return if (path.last() == "end") {
                listOf(path)
            } else {
                paths.getValue(path.last())
                    .filter { allow(it, path) }
                    .flatMap { searchPaths(path + it) }
            }
        }

    }


    fun countPaths(allow: Allow): Int {
        return PathSearcher(allow).searchPaths().size
    }


    fun part1(): Int {
        return countPaths(PartOneAllow())
    }


    fun part2(): Int {
        return countPaths(PartTwoAllow())
    }


    println(part1())
    println(part2())

}
