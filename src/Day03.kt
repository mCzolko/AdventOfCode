fun main() {

    fun part1(input: List<String>): Int {
        val calcList = mutableMapOf<Int, Int>()

        input.forEachIndexed { i, bits ->
            bits.forEachIndexed { index, c ->
                if (!calcList.contains(index)) {
                    calcList.set(index, 0)
                }

                if (c == '1') {
                    calcList.set(index, calcList.get(index)!!.plus(1))
                }
            }
        }

        var binaryString = ""
        calcList.values.forEach {
            if (it > input.size.div(2)) {
                binaryString += '1'
            } else {
                binaryString += '0'
            }
        }

        val binaryStringInverted = binaryString.toInt(2).inv().toUInt().toString(2).takeLast(binaryString.length)

        return binaryString.toInt(2) * binaryStringInverted.toInt(2)
    }

    fun part2(input: List<String>): Int {
        fun find(list: List<String>, zeroPriority: Boolean = false, position: Int = 0): List<String> {
            if (list.size == 1) return list

            val count = list.count { it[position] == '1' }
            val priorityMajor = count > list.size.minus(1).div(2)
            val by = if (if (zeroPriority) !priorityMajor else priorityMajor) '1' else '0'

            return find(list.filter { it[position] == by }, zeroPriority, position + 1)
        }

        return find(input).first().toInt(2) * find(input, true).first().toInt(2)
    }

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
