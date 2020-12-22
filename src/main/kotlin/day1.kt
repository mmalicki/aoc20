import java.io.File

object Day1First {
    @JvmStatic
    fun main(args: Array<String>) {
        val entries = loadLines(1)
            .map(String::toInt)
        println(findAnswer(entries))
    }

    fun findAnswer(entries: List<Int>): Int? {
        (0..entries.lastIndex).forEach { i ->
            (i + 1..entries.lastIndex).forEach { j ->
                if (entries[i] + entries[j] == 2020) return entries[i] * entries[j]
            }
        }
        return null
    }
}

object Day1Second {
    @JvmStatic
    fun main(args: Array<String>) {
        val entries = loadLines(1)
            .map(String::toInt)
        println(findAnswer(entries))
    }

    fun findAnswer(entries: List<Int>): Int? {
        (0..entries.lastIndex).forEach { i ->
            (i + 1..entries.lastIndex).forEach { j ->
                (j + 1..entries.lastIndex).forEach { k ->
                    if (entries[i] + entries[j] + entries[k] == 2020) return entries[i] * entries[j] * entries[k]
                }
            }
        }
        return null
    }
}