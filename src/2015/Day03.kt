fun main() {

    val inputRaw = readInput("2015/Day03").first().trim()
    val input = inputRaw.split("").filter { it != "" }


    fun partOne(): Int {
        val housesVisited = mutableListOf<String>()
        housesVisited.add("0|0")
        var position = Pair(0, 0)

        input.forEach {
            when (it) {
                "^" -> position = Pair(position.first.plus(1), position.second)
                "v" -> position = Pair(position.first.minus(1), position.second)
                ">" -> position = Pair(position.first, position.second.plus(1))
                "<" -> position = Pair(position.first, position.second.minus(1))
            }
            housesVisited.add("${position.first}|${position.second}")
        }

        return housesVisited.distinct().size
    }


    fun partTwo(): Int {
        return 0
    }


    println(partOne())
    println(partTwo())

}
