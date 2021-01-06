import kotlin.math.ceil
import kotlin.math.floor

object Day5First {
    @JvmStatic
    fun main(args: Array<String>) {
        val seats = loadLines(5).map { seatInput -> Seat(seatInput) }
        val seatWithHighestId = seats.maxByOrNull { it.seatId }!!
        println(seatWithHighestId.seatId)
    }
    class Seat(input: String) {
        val rowsCharacters = input.take(7)
        val columnsCharacters = input.takeLast(3)
        val seatId get() = row * 8 + column
        val row: Int get() = calculateSeatPosition(rowsCharacters, 127, 'B', 'F')
        val column: Int get() = calculateSeatPosition(columnsCharacters, 7, 'R', 'L')
        fun calculateSeatPosition(characters: String, end: Int, higherHalfLetter: Char, lowerHalfLetter: Char): Int {
            return characters.fold(0 to end) { (start, currEnd), c ->
                when (c) {
                    higherHalfLetter -> higherHalf(start, currEnd) to currEnd
                    lowerHalfLetter -> start to lowerHalf(start, currEnd)
                    else -> throw IllegalStateException()
                }
            }.first
        }
        fun lowerHalf(start: Int, end: Int) = start + floor((end - start) / 2.0).toInt()
        fun higherHalf(start: Int, end: Int) = start + ceil((end - start) / 2.0).toInt()
    }
}

object Day5Second {
    @JvmStatic
    fun main(args: Array<String>) {
        val seats = loadLines(5)
            .map { seatInput -> Seat(seatInput) }
        val seatIds = seats.mapTo(mutableSetOf()) { it.seatId }
        val minSeatId = 1 * 8 + 0
        val maxSeatId = 126 * 8 + 7
        val mySeatId = (minSeatId..maxSeatId).single { seatId ->
            seatIds.containsAll(listOf(seatId - 1, seatId + 1)) && !seatIds.contains(seatId)
        }
        println(mySeatId)
    }
   class Seat(input: String) {
        val rowsCharacters = input.take(7)
        val columnsCharacters = input.takeLast(3)
        val seatId get() = row * 8 + column
        val row: Int get() = calculateSeatPosition(rowsCharacters, 127, 'B', 'F')
        val column: Int get() = calculateSeatPosition(columnsCharacters, 7, 'R', 'L')
        fun calculateSeatPosition(characters: String, end: Int, higherHalfLetter: Char, lowerHalfLetter: Char): Int {
            return characters.fold(0 to end) { (start, currEnd), c ->
                when (c) {
                    higherHalfLetter -> higherHalf(start, currEnd) to currEnd
                    lowerHalfLetter -> start to lowerHalf(start, currEnd)
                    else -> throw IllegalStateException()
                }
            }.first
        }
        fun lowerHalf(start: Int, end: Int) = start + floor((end - start) / 2.0).toInt()
        fun higherHalf(start: Int, end: Int) = start + ceil((end - start) / 2.0).toInt()
       override fun toString(): String {
           return "Seat(seatId=$seatId, row=$row, column=$column)"
       }
   }
}