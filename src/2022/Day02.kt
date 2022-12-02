package `2022`

import readInput

enum class Move(val weight: Int) {
    ROCK(1), PAPER(2), SCISSORS(3)
}

fun Move.winMove(): Move = when (this) {
    Move.ROCK -> Move.PAPER
    Move.SCISSORS -> Move.ROCK
    Move.PAPER -> Move.SCISSORS
}

fun Move.counterMove(): Move = when (this) {
    Move.ROCK -> Move.SCISSORS
    Move.SCISSORS -> Move.PAPER
    Move.PAPER -> Move.ROCK
}

fun Pair<Move, Move>.resultOfFirst(): Result = when {
    this.first == this.second -> Result.DRAW
    this.first == Move.ROCK && this.second == Move.SCISSORS -> Result.WIN
    this.first == Move.PAPER && this.second == Move.ROCK -> Result.WIN
    this.first == Move.SCISSORS && this.second == Move.PAPER -> Result.WIN
    else -> Result.LOSE
}

fun Pair<Move, Move>.resultOfSecond(): Result =
    this.resultOfFirst().opposite()


enum class Result {
    LOSE, DRAW, WIN
}

fun Result.opposite(): Result = when (this) {
    Result.LOSE -> Result.WIN
    Result.DRAW -> Result.DRAW
    Result.WIN -> Result.LOSE
}


fun main() {

    fun part1(moves: List<Pair<Move, Move>>): Int = moves.sumOf {
        val me = it.second

        when (it.resultOfSecond()) {
            Result.LOSE -> 0 + me.weight
            Result.WIN -> 6 + me.weight
            Result.DRAW -> 3 + me.weight
        }
    }


    fun part2(moves: List<Pair<Move, Move>>): Int = moves.sumOf {
        val (elf, end) = it

        when (Result.values()[end.weight - 1]) {
            Result.LOSE -> 0 + elf.counterMove().weight
            Result.WIN -> 6 + elf.winMove().weight
            Result.DRAW -> elf.weight + 3
        }
    }


    fun mapMove(move: String): Move = when (move) {
        "A" -> Move.ROCK
        "B" -> Move.PAPER
        "X" -> Move.ROCK
        "Y" -> Move.PAPER
        else -> Move.SCISSORS
    }

    val input = readInput("2022/Day02")
    val moves = input
        .map { it.split(" ") }
        .map { Pair(mapMove(it[0]), mapMove(it[1])) }

    println(part1(moves))
    println(part2(moves))

}
