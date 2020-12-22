object Day3First {
    @JvmStatic
    fun main(args: Array<String>) {
        val lines = loadLines(3)
        val linesCount = lines.size
        val lineSize = lines[0].length
        val trees = countTrees(linesCount, lines, lineSize, 3, 1)
        println(trees)
    }

    private fun countTrees(linesCount: Int, lines: List<String>, lineSize: Int, right: Int, down: Int): Int {
        var x = 0
        var y = 0
        var trees = 0
        while (y < linesCount) {
            if (lines[y][x] == '#') {
                trees++
            }
            x = (x + right) % lineSize
            y += down
        }
        return trees
    }
}

object Day3Second {
    @JvmStatic
    fun main(args: Array<String>) {
        val lines = loadLines(3)
        val linesCount = lines.size
        val lineSize = lines[0].length
        val treesSums = listOf(
            countTrees(linesCount, lines, lineSize, 1, 1),
            countTrees(linesCount, lines, lineSize, 3, 1),
            countTrees(linesCount, lines, lineSize, 5, 1),
            countTrees(linesCount, lines, lineSize, 7, 1),
            countTrees(linesCount, lines, lineSize, 1, 2),
        ).reduce { sumsMultiplied, sum -> sumsMultiplied * sum }
        println(treesSums)
    }

    private fun countTrees(linesCount: Int, lines: List<String>, lineSize: Int, right: Int, down: Int): Int {
        var x = 0
        var y = 0
        var trees = 0
        while (y < linesCount) {
            if (lines[y][x] == '#') {
                trees++
            }
            x = (x + right) % lineSize
            y += down
        }
        return trees
    }
}