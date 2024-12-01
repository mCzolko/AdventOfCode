import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(this.toByteArray())).toString(16).padStart(32, '0')
}

/**
 * Median
 */
fun median(input: List<Long>): Long {
    input.sorted()
    val middle: Int = input.size / 2

    if (input.size % 2 == 1)
        return input[middle]
    else
        return (input[middle - 1] + input[middle]) / 2
}

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

