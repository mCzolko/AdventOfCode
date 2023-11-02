package `2015`

import md5

fun main() {

    fun findHash(input: String, prefix: String): Int {
        var i = 0

        while (true) {
            val hash = "$input${i}"
                .md5()
                .substring(0, prefix.length)

            if (hash.startsWith(prefix)) {
                return i
            }

            i++
        }
    }

    fun part1(input: String): Int {
        return findHash(input, "00000")
    }

    fun part2(input: String): Int {
        return findHash(input, "000000")
    }

    val input = "ckczppom"
    println(part1(input))
    println(part2(input))

}
