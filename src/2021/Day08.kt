fun main() {

    fun part1(input: List<String>): Int {
        return input
            .map { it.split(" | ").last() }
            .map { it.split(" ") }
            .flatten()
            .count { it.length in setOf(2, 3, 4, 7) }
    }

    fun part2(input: List<String>): Int {
        val mapped = input.map { line ->
            val (words, keys) = line.split(" | ").map { it.split(' ').map { it.toSet() } }
            val wordsBySize = List(8) { len -> words.filter { it.size == len } }

            val arr = Array(10) { setOf<Char>() }
            arr[1] = wordsBySize[2].first()
            arr[7] = wordsBySize[3].first()
            arr[4] = wordsBySize[4].first()
            arr[8] = wordsBySize[7].first()
            arr[6] = wordsBySize[6].first {  setOf(it + arr[1]).contains(arr[8]) }
            arr[5] = wordsBySize[5].first { !setOf(it + arr[6]).contains(arr[8]) }
            arr[2] = wordsBySize[5].first {  setOf(it + arr[5]).contains(arr[8]) }
            arr[9] = wordsBySize[6].first { !setOf(it + arr[4]).contains(arr[8]) }
            arr[0] = wordsBySize[6].first {  setOf(it + arr[5]).contains(arr[8]) }
            arr[3] = wordsBySize[5].first { !arr.contains(it) }

            keys.map { arr.indexOf(it) }.joinToString("").toInt()
        }

        return mapped.sum()
    }

    val input = readInput("2021/Day08")

    println(part1(input))
    println(part2(input))

}
