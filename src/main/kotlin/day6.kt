object Day6First {
    @JvmStatic
    fun main(args: Array<String>) {
        val groups = loadLines(6)
            .joinToString("\n")
            .split("\n\n")
            .map { groupString -> Group(groupString.split("\n")) }

        val answeredQuestionsCountSum = groups.sumBy { it.answeredQuestionsCount }
        println(answeredQuestionsCountSum)
    }

    data class Group(val answers: List<String>) {
        val answeredQuestionsCount: Int get() = answers.flatMapTo(mutableSetOf()) { it.toSet() }.size
    }
}

object Day6Second {
    @JvmStatic
    fun main(args: Array<String>) {
        val groups = loadLines(6)
            .joinToString("\n")
            .split("\n\n")
            .map { groupString -> Group(groupString.split("\n")) }

        val answeredQuestionsCountSum = groups.sumBy { it.answeredQuestionsCount }
        println(answeredQuestionsCountSum)
    }

    data class Group(val answers: List<String>) {
        val answeredQuestionsCount get() = answers
            .flatMap { it.toSet() }
            .groupBy { it }
            .count { (_, answersCount) -> answersCount.size == answers.size }
    }
}