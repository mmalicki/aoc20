object Day4First {
    data class Passport(
        val byr: String?,
        val iyr: String?,
        val eyr: String?,
        val hgt: String?,
        val hcl: String?,
        val ecl: String?,
        val pid: String?,
        val cid: String?
    ) {
        fun isValid(): Boolean {
            return byr != null && iyr != null && eyr != null && hgt != null && hcl != null && ecl != null && pid != null
        }
    }

    fun createPassport(passportString: String): Passport {
        val fieldNameToValue = passportString.split(" ", "\n").map {
            val (fieldName, value) = it.split(":")
            fieldName to value
        }.toMap()
        return Passport(
            byr = fieldNameToValue["byr"],
            iyr = fieldNameToValue["iyr"],
            eyr = fieldNameToValue["eyr"],
            hgt = fieldNameToValue["hgt"],
            hcl = fieldNameToValue["hcl"],
            ecl = fieldNameToValue["ecl"],
            pid = fieldNameToValue["pid"],
            cid = fieldNameToValue["cid"]
        )
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val passportStrings = loadLines(4).joinToString("\n").split("\n\n")
        val passports = passportStrings.map { createPassport(it) }
        val validPassportsCount = passports.count { it.isValid() }
        println(validPassportsCount)
    }
}

object Day4Second {
    data class Passport(
        val byr: String?,
        val iyr: String?,
        val eyr: String?,
        val hgt: String?,
        val hcl: String?,
        val ecl: String?,
        val pid: String?,
        val cid: String?
    ) {
        fun isValid(): Boolean {
            return byr?.toIntOrNull()?.let { it in (1920..2002) } ?: false &&
                iyr?.toIntOrNull()?.let { it in (2010..2020) } ?: false &&
                eyr?.toIntOrNull()?.let { it in (2020..2030) } ?: false &&
                hgt?.let {
                    val height = it.takeWhile { it.isDigit() }.toIntOrNull() ?: return false
                    val unit = it.takeLastWhile { it.isLetter() }
                    when (unit) {
                        "cm" -> height in (150..193)
                        "in" -> height in (59..76)
                        else -> false
                    }
                } ?: false &&
                hcl?.let {
                    it.firstOrNull() == '#' && it.substring(1).let { substring ->
                        substring.length == 6 && (substring.all { subChar ->
                            subChar in ('0'..'9') || subChar in ('a'..'f')
                        })
                    }
                } ?: false &&
                ecl?.let { it in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth") } ?: false &&
                pid?.let { it.length == 9 && it.all { it.isDigit() }} ?: false
        }
    }

    fun createPassport(passportString: String): Passport {
        val fieldNameToValue = passportString.split(" ", "\n").map {
            val (fieldName, value) = it.split(":")
            fieldName to value
        }.toMap()
        return Passport(
            byr = fieldNameToValue["byr"],
            iyr = fieldNameToValue["iyr"],
            eyr = fieldNameToValue["eyr"],
            hgt = fieldNameToValue["hgt"],
            hcl = fieldNameToValue["hcl"],
            ecl = fieldNameToValue["ecl"],
            pid = fieldNameToValue["pid"],
            cid = fieldNameToValue["cid"]
        )
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val passportStrings = loadLines(4).joinToString("\n").split("\n\n")
        val passports = passportStrings.map { createPassport(it) }
        val validPassportsCount = passports.count { it.isValid() }
        println(validPassportsCount)
    }
}