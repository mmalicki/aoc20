object Day7First {
    @JvmStatic
    fun main(args: Array<String>) {
        val bags = loadLines(7)
            .map { parseBag(it) }

        println(findAllCompatibleBags(bags, "shiny gold"))
    }

    fun findAllCompatibleBags(bags: List<Bag>, bagName: String): Int {
        val resultBagNames = mutableSetOf<String>()
        val matchingBags = bags.filter { bag -> bag.capacity.any { spec -> spec.color == bagName } }
        resultBagNames.addAll(matchingBags.map { it.color })
        var matchingColors = matchingBags.map { it.color }
        do {
            val next = bags.filter { bag -> bag.capacity.any { spec -> spec.color in matchingColors } }
            resultBagNames.addAll(next.map { it.color })
            matchingColors = next.map { it.color }
        } while (next.isNotEmpty())
        return resultBagNames.size
    }

    fun parseBag(input: String): Bag {
        val color = input.substringBefore("bags").trim()
        val capacity = input.substringAfter("contain").trim()
        if (capacity == "no other bags.") {
            return Bag(color, emptyList())
        }
        val bagCapacity = capacity.split(",").map { bagDescription ->
            val (count, color1, color2) = bagDescription.trim().split(" ")
            BagSpec(
                color = "$color1 $color2",
                count = count.toInt()
            )
        }
        return Bag(color = color, capacity = bagCapacity)
    }

    data class Bag(
        val color: String,
        val capacity: List<BagSpec>,
    )

    data class BagSpec(
        val color: String,
        val count: Int
    )
}

object Day7Second {
    @JvmStatic
    fun main(args: Array<String>) {
        val bags = loadLines(7)
            .map { parseBag(it) }

        println(countBags(bags, "shiny gold"))
    }

    fun countBags(bags: List<Bag>, bagName: String): Int {
        val bagNameToContainedBags = bags.associateBy({ it.color }, { it.capacity })

        fun calculateBagCount(bagName: String): Int {
            val currBags = bagNameToContainedBags[bagName]!!
            return currBags.sumBy { it.count } + currBags.sumBy { it.count * calculateBagCount(it.color) }
        }

        return calculateBagCount(bagName)
    }

    fun parseBag(input: String): Bag {
        val color = input.substringBefore("bags").trim()
        val capacity = input.substringAfter("contain").trim()
        if (capacity == "no other bags.") {
            return Bag(color, emptyList())
        }
        val bagCapacity = capacity.split(",").map { bagDescription ->
            val (count, color1, color2) = bagDescription.trim().split(" ")
            BagSpec(
                color = "$color1 $color2",
                count = count.toInt()
            )
        }
        return Bag(color = color, capacity = bagCapacity)
    }

    data class Bag(
        val color: String,
        val capacity: List<BagSpec>,
    )

    data class BagSpec(
        val color: String,
        val count: Int
    )
}