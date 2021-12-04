import java.lang.StringBuilder
import java.math.BigInteger


fun main() {

    fun part1(input: Map<String, List<Pair<Int, BigInteger>>>): BigInteger {
        val memory = mutableMapOf<Int, BigInteger>()

        input.forEach { entry ->
            val mask = entry.key
            val replaceCharMap = mutableMapOf<Int, Char>()
            mask.forEachIndexed { index, it ->
                if (it != 'X') {
                    replaceCharMap[index] = it
                }
            }

            entry.value.forEach {
                val value = it.second.toString(2).padStart(mask.length, '0')
                val sb = StringBuilder(value).also { sb ->
                    for ((at, char) in replaceCharMap) {
                        sb.setCharAt(at, char)
                    }
                }

                memory[it.first] = sb.toString().toBigInteger(2)
            }
        }

        return memory.values.fold(BigInteger.ZERO) { acc, e -> acc + e }
    }

    val input = readInput("2020/Day14")
    val maskedMap = mutableMapOf<String, MutableList<Pair<Int, BigInteger>>>()

    var mask = ""
    input.forEach {
        val splitEqual = it.split(" = ")
        if (splitEqual.first() == "mask") {
            mask = splitEqual.last()
            maskedMap[mask] = mutableListOf()
        } else {
            maskedMap[mask]?.add(Pair(
                splitEqual.first().drop(4).dropLast(1).toInt(),
                splitEqual.last().toBigInteger()
            ))
        }
    }

    println(part1(maskedMap))
}
