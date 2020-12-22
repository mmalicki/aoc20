import java.io.File

fun loadLines(day: Int): List<String> {
    return File("src/main/resources/day$day.txt").readLines()
}