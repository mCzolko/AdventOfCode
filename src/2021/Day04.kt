package `2021`

import readInput

fun main() {

    open class Cell(var n: Int)
    class MarkedCell(n: Int) : Cell(n)
    class UnmarkedCell(n: Int) : Cell(n)

    class BingoBoard(var rows: List<List<Int>>) {

        private fun isWinningByGrid(board: List<List<Cell>>): Boolean {
            val rowCount = board.first().size

            for (rowIndex in 0 until rowCount) {
                // check row
                val row = board[rowIndex]
                if (row.filterIsInstance<MarkedCell>().size == rowCount) return true
                // check column
                val column = List(board.size) { board[it][rowIndex] }
                if (column.filterIsInstance<MarkedCell>().size == rowCount) return true
            }

            return false
        }

        fun map(drawnNumbers: List<Int>): List<Cell> {
            return rows.flatten().map {
                if (drawnNumbers.contains(it)) MarkedCell(it) else UnmarkedCell(it)
            }
        }

        fun isWinning(drawnNumbers: List<Int>): Boolean {
            return isWinningByGrid(map(drawnNumbers).windowed(rows.first().size, rows.first().size))
        }

        fun calc(drawnNumbers: List<Int>): Int {
            val unmarkedNumbers = map(drawnNumbers).filter { it is UnmarkedCell }.map { it.n }
            return unmarkedNumbers.reduce { acc, i -> acc.plus(i) } * drawnNumbers.last()
        }
    }

    val input = readInput("2021/Day04").toMutableList()
    val drawnNumbers = input.first().split(',').map { it.toInt() }.toMutableList()
    input.removeFirst()
    val boardsInput = input.filter { it.isNotEmpty() }.windowed(5, 5)
    val intBoards = boardsInput.map { it.map { it.split(' ').filter { it.isNotEmpty() }. map { it.toInt() } } }
    val bingoBoards = intBoards.map { BingoBoard(it) }


    fun part1(input: List<BingoBoard>, drawnNumbers: List<Int>): Int {
        for (i in drawnNumbers.indices) {
            input.forEachIndexed { index, bingoBoard ->
                val drawn = drawnNumbers.take(i)
                if (bingoBoard.isWinning(drawn)) {
                    return bingoBoard.calc(drawn)
                }
            }
        }

        return -1
    }


    fun part2(input: List<BingoBoard>, drawnNumbers: List<Int>): Int {
        for (i in drawnNumbers.indices) {
            input.forEachIndexed { index, bingoBoard ->
                val drawn = drawnNumbers.take(i)
                if (bingoBoard.isWinning(drawn)) {
                    if (input.size == 1) {
                        return input.first().calc(drawn)
                    }

                    val lessBoards = input.toMutableList()
                    lessBoards.removeAt(index)

                    return part2(lessBoards, drawnNumbers)
                }
            }
        }

        return -1
    }


    println(part1(bingoBoards, drawnNumbers))
    println(part2(bingoBoards, drawnNumbers))
}
