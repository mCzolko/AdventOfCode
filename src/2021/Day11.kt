fun main() {

    val input = readInput("2021/Day11")
        .map { it.windowed(1).map { it.toInt() } }

    data class DumboOctopus(val x: Int, val y: Int, var charge: Int)

    val grid: MutableList<DumboOctopus> = mutableListOf()

    for (row in input.indices) {
        for (cell in 0 until input[row].size) {
            grid.add(DumboOctopus(cell, row, input[row][cell]))
        }
    }

    fun getPositionAt(x: Int, y: Int): DumboOctopus? {
        if (x < 0 || x >= input.first().size || y < 0) return null
        return grid.getOrNull(y * input.first().size + x)
    }

    fun DumboOctopus.nearBy(): List<DumboOctopus> {
        val n = getPositionAt(x, y - 1)
        val s = getPositionAt(x, y + 1)
        val w = getPositionAt(x - 1, y)
        val e = getPositionAt(x + 1, y)
        val nw = getPositionAt(x - 1, y - 1)
        val ne = getPositionAt(x + 1, y - 1)
        val sw = getPositionAt(x - 1, y + 1)
        val se = getPositionAt(x + 1, y + 1)
        return listOfNotNull(n, s, w, e, nw, ne, sw, se)
    }


    fun part1(): Int {
        var flashes = 0

        for (step in 0..100) {
            flashes += grid.count { it.charge == 0 }
            grid.forEach { it.charge = it.charge + 1 }

            while (true) {
                val octopusesToFlash = grid.filter { it.charge > 9 }

                if (octopusesToFlash.isEmpty()) {
                    break
                }

                octopusesToFlash.forEach { it.charge = 0 }
                octopusesToFlash.forEach {
                    it.nearBy().filter { it.charge != 0 }.forEach { o -> o.charge = o.charge + 1 }
                }
            }
        }
        return flashes
    }


    fun part2(): Int {
        var step = 0

        while (true) {
            step++
            if (grid.count { it.charge == 0 } == 100) {
                return step + 100
            }

            grid.forEach { it.charge = it.charge + 1 }

            while (true) {
                val octopusesToFlash = grid.filter { it.charge > 9 }

                if (octopusesToFlash.isEmpty()) {
                    break
                }

                octopusesToFlash.forEach { it.charge = 0 }
                octopusesToFlash.forEach {
                    it.nearBy().filter { a -> a.charge != 0 }.forEach { o -> o.charge = o.charge + 1 }
                }
            }
        }
    }


    println(part1())
    println(part2())

}
