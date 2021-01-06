sealed class Instruction
data class Acc(val value: Int) : Instruction()
data class Jmp(val offset: Int) : Instruction()
data class Nop(val value: Int) : Instruction()

object Day8First {
    @JvmStatic
    fun main(args: Array<String>) {
        val instructions = loadLines(8)
            .map { parseInstruction(it) }
        val execution = Execution(instructions)
        println(execution.execute())
    }

    fun parseInstruction(instructionString: String): Instruction {
        val (instructionName, value) = instructionString.split(" ")
        return when (instructionName) {
            "nop" -> Nop(value.toInt())
            "acc" -> Acc(value.toInt())
            "jmp" -> Jmp(value.toInt())
            else -> throw IllegalArgumentException(instructionName)
        }
    }

    class Execution(val instructions: List<Instruction>) {
        val visitedInstructions = Array(instructions.size) { false }.toMutableList()
        var instructionPointer = 0
        var accumulator = 0
        fun execute(): Int {
            do {
                val currentInstruction = instructions[instructionPointer]
                visitedInstructions[instructionPointer] = true
                when (currentInstruction) {
                    is Acc -> {
                        accumulator += currentInstruction.value
                        instructionPointer += 1
                    }
                    is Jmp -> instructionPointer += currentInstruction.offset
                    is Nop -> {
                        instructionPointer += 1
                    }
                }
            } while (!visitedInstructions[instructionPointer])

            return accumulator
        }
    }
}


object Day8Second {
    @JvmStatic
    fun main(args: Array<String>) {
        val instructions = loadLines(8)
            .map { parseInstruction(it) }
        val alternatives = generateAlternatives(instructions)
        val correctProgramAcc = alternatives
            .mapNotNull { execute(it) }
            .first()
        println(correctProgramAcc)
    }

    fun parseInstruction(instructionString: String): Instruction {
        val (instructionName, value) = instructionString.split(" ")
        return when (instructionName) {
            "nop" -> Nop(value.toInt())
            "acc" -> Acc(value.toInt())
            "jmp" -> Jmp(value.toInt())
            else -> throw IllegalArgumentException(instructionName)
        }
    }

    fun generateAlternatives(instructions: List<Instruction>): List<List<Instruction>> {
        return instructions.mapIndexedNotNull { index, instruction ->
            when (instruction) {
                is Jmp -> instructions.mapIndexed { i, instr -> if (i == index) Nop(instruction.offset) else instr }
                is Nop -> instructions.mapIndexed { i, instr -> if (i == index) Jmp(instruction.value) else instr }
                is Acc -> null
            }
        }
    }

    fun execute(instructions: List<Instruction>): Int? {
        val visitedInstructions = Array(instructions.size) { false }.toMutableList()
        var instructionPointer = 0
        var accumulator = 0
        do {
            val currentInstruction = instructions[instructionPointer]
            visitedInstructions[instructionPointer] = true
            when (currentInstruction) {
                is Acc -> {
                    accumulator += currentInstruction.value
                    instructionPointer += 1
                }
                is Jmp -> instructionPointer += currentInstruction.offset
                is Nop -> {
                    instructionPointer += 1
                }
            }
        } while (instructionPointer < instructions.size && !visitedInstructions[instructionPointer])

        return if (instructionPointer == instructions.size) {
            accumulator
        } else {
            null
        }
    }
}