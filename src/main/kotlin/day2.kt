import java.io.File

object Day2First {
    data class PasswordPolicy(
        val letter: Char,
        val countRange: IntRange,
        val password: String
    ) {
        fun isValid() = password.count { it == letter } in countRange
    }
    @JvmStatic
    fun main(args: Array<String>) {
        val passwordPolicies = File("src/main/resources/day2.txt")
            .readLines()
            .map { line ->
                val (rangeString, letterString, passwordString) = line.split(" ")
                val (start, end) = rangeString.split("-")
                val range = IntRange(start.toInt(), end.toInt())
                val letter = letterString.first()
                PasswordPolicy(
                    letter = letter,
                    countRange = range,
                    password = passwordString
                )
            }
        val validPasswordsCount = passwordPolicies.count { passwordPolicy -> passwordPolicy.isValid() }
        println(validPasswordsCount)
    }
}

object Day2Second {
    data class PasswordPolicy(
        val letter: Char,
        val countRange: IntRange,
        val password: String
    ) {
        fun isValid() = (password[countRange.first - 1] == letter) xor (password[countRange.last - 1] == letter)
    }
    @JvmStatic
    fun main(args: Array<String>) {
        val passwordPolicies = File("src/main/resources/day2.txt")
            .readLines()
            .map { line ->
                val (rangeString, letterString, passwordString) = line.split(" ")
                val (start, end) = rangeString.split("-")
                val range = IntRange(start.toInt(), end.toInt())
                val letter = letterString.first()
                PasswordPolicy(
                    letter = letter,
                    countRange = range,
                    password = passwordString
                )
            }
        val validPasswordsCount = passwordPolicies.count { passwordPolicy -> passwordPolicy.isValid() }
        println(validPasswordsCount)
    }
}