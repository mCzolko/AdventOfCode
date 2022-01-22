package `2016`

import readInput
import kotlin.math.absoluteValue

fun main() {

    val input = readInput("2016/Day01").first().split(", ").map { Pair(it[0], it.takeLast(it.length - 1).toInt()) }
    val visitedPlaces = mutableListOf<Pair<Int, Int>>()

    val aims = listOf('N', 'E', 'S', 'W')
    var aim = 'N'
    visitedPlaces.add(Pair(0, 0))
    var placeVisitedTwice: Pair<Int, Int>? = null

    input.forEach {
        var aimIndex = aims.indexOf(aim)
        val lastPlace = visitedPlaces.last()

        when (it.first) {
            'R' -> if (aimIndex == 3) aimIndex = 0 else aimIndex += 1
            'L' -> if (aimIndex == 0) aimIndex = 3 else aimIndex -= 1
        }

        aim = aims.get(aimIndex)

        for (step in 0 until it.second) {
            var newVisitedPlace: Pair<Int, Int> = Pair(0, 0)

            when (aim) {
                'N' -> newVisitedPlace = Pair(lastPlace.first, lastPlace.second + step.plus(1))
                'S' -> newVisitedPlace = Pair(lastPlace.first, lastPlace.second - step.plus(1))
                'E' -> newVisitedPlace = Pair(lastPlace.first + step.plus(1), lastPlace.second)
                'W' -> newVisitedPlace = Pair(lastPlace.first - step.plus(1), lastPlace.second)
            }

            if (placeVisitedTwice === null && visitedPlaces.contains(newVisitedPlace)) {
                placeVisitedTwice = newVisitedPlace
            }

            visitedPlaces.add(newVisitedPlace)
        }
    }


    fun partOne(): Int {
        return visitedPlaces.last().first.absoluteValue + visitedPlaces.last().second.absoluteValue
    }


    fun partTwo(): Int {
        return placeVisitedTwice?.first?.absoluteValue!! + placeVisitedTwice?.second?.absoluteValue!!
    }


    println(partOne())
    println(partTwo())

}
