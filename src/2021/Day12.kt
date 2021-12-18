fun main() {

    fun isUppercase(string: String): Boolean {
        return string.toCharArray().first().isUpperCase()
    }

    fun searchPath(paths: Map<String, List<String>>, path: List<String> = listOf("start")): List<List<String>> {
        if (path.last() == "end") {
            return listOf(path)
        } else {
            return paths.getValue(path.last())
                .filter { isUppercase(it) || it !in path }
                .flatMap {
                    searchPath(paths, path + it)
                }
        }
    }

    fun part1(paths: Map<String, List<String>>): Int {
        return searchPath(paths).size
    }


    fun part2(): Int {
        return 0
    }


    val input = readInput("2021/Day12")
        .map { it.split("-") }
        .flatMap {
            listOf(
                it.first() to it.last(),
                it.last() to it.first()
            )
        }
        .groupBy({ it.first }, { it.second })
    println(input)

    println(part1(input))
    println(part2())

}
