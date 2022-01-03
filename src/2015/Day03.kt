fun main() {

    val inputRaw = readInput("2015/Day03").first().trim()
    val input = inputRaw.split("").filter { it != "" }


    fun visitHouses(instructions: List<String>): List<String> {
        val housesVisited = mutableListOf<String>()
        housesVisited.add("0|0")
        var position = Pair(0, 0)

        instructions.forEach {
            when (it) {
                "^" -> position = Pair(position.first.plus(1), position.second)
                "v" -> position = Pair(position.first.minus(1), position.second)
                ">" -> position = Pair(position.first, position.second.plus(1))
                "<" -> position = Pair(position.first, position.second.minus(1))
            }
            housesVisited.add("${position.first}|${position.second}")
        }

        return housesVisited
    }


    fun partOne(): Int {
        return visitHouses(input).distinct().size
    }


    fun partTwo(): Int {
        val santaHouses = input.filterIndexed { index, s -> index % 2 == 0 }
        val roboSantaHouses = input.filterIndexed { index, s -> index % 2 != 0 }

        return (visitHouses(santaHouses) + visitHouses(roboSantaHouses)).distinct().size
    }


    println(partOne())
    println(partTwo())

}
