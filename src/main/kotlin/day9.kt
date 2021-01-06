object Day9First {
    @JvmStatic
    fun main(args: Array<String>) {
        val numbers = loadLines(9).map { it.toLong() }
        val encodings = numbers.windowed(26, 1)
        val firstNotValidEncoding = encodings.first { encoding ->
            val number = encoding.last()
            !hasAddingPair(encoding.dropLast(1), number)
        }
        println(firstNotValidEncoding.last())
    }

    private fun hasAddingPair(encoding: List<Long>, number: Long): Boolean {
        encoding.forEachIndexed { i, e1 ->
            (i..encoding.lastIndex).forEach { j ->
                if (e1 + encoding[j] == number) {
                    return true
                }
            }
        }
        return false
    }
}

object Day9Second {
    @JvmStatic
    fun main(args: Array<String>) {
        val searchedNumber = 14144619L
        val numbers = loadLines(9).map { it.toLong() }
        println(findEncryptionAnswer(numbers, searchedNumber))
    }

    private fun findEncryptionAnswer(numbers: List<Long>, searchedNumber: Long): Long? {
        (0..numbers.size - 2).forEach { i ->
            var windowSize = 2
            do {
                val subset = numbers.subList(i,  i + windowSize)
                if (subset.sum() == searchedNumber) {
                    val sortedSubset = subset.sorted()
                    return sortedSubset.first() + sortedSubset.last()
                }
                windowSize += 1
            } while (subset.sum() < searchedNumber && (i + windowSize) < numbers.size)
        }
        throw RuntimeException("no match")
    }
}