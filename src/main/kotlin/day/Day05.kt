package day

import day.Day05.PrintRules.Companion.readInput
import util.Grid
import util.switchElements
import util.toIntList


object Day05 : Day("05", "143", "123") {
    override fun examplePartOne() = getExampleRaw().readInput().getValidPrintNumber()
    override fun examplePartTwo() = getExampleRaw().readInput().getInvalidPrintNumber()
    override fun solvePartOne() = getInputRaw().readInput().getValidPrintNumber()
    override fun solvePartTwo() = getInputRaw().readInput().getInvalidPrintNumber()

    data class PrintRules(val rules: List<PrintRule>, val instructions: Grid<Int>) {

        fun getValidPrintNumber() = validInstructions().sumOf { it[it.size / 2] }.toString()
        fun getInvalidPrintNumber() = invalidInstructions().map {
            val correctedInstruction = it.toMutableList()
            val currentRules = rules.filter { rule -> correctedInstruction.containsAll(rule.numbers) }
            correctInstruction(currentRules, correctedInstruction)
        }.sumOf { it[it.size / 2] }.toString()

        private fun correctInstruction(
            currentRules: List<PrintRule>,
            correctedInstruction: MutableList<Int>
        ): MutableList<Int> {
            while (!validateRulesAnInstructions(currentRules, correctedInstruction)) {
                applyRule(currentRules, correctedInstruction)
            }
            return correctedInstruction
        }

        private fun applyRule(currentRules: List<PrintRule>, correctedInstruction: MutableList<Int>) {
            val wrongRule = currentRules.first { rule ->
                !rule.regex.containsMatchIn(correctedInstruction.toString())
            }
            correctedInstruction.switchElements(wrongRule.first, wrongRule.second)
        }

        private fun validInstructions(): Grid<Int> = instructions.filter { instruction ->
            val currentRules = rules.filter { rule -> instruction.containsAll(rule.numbers) }
            validateRulesAnInstructions(currentRules, instruction)
        }

        private fun validateRulesAnInstructions(currentRules: List<PrintRule>, instruction: List<Int>) =
            currentRules.all { rule ->
                rule.regex.containsMatchIn(instruction.toString())
            }

        private fun invalidInstructions(): Grid<Int> = instructions.subtract(validInstructions()).toList()

        companion object {
            fun String.readInput(): PrintRules {
                val (rulesInput, instructionsInput) = split("\n\n").map { it.split("\n") }
                val rules = rulesInput.map { input ->
                    val numbers = input.split("|").toIntList()
                    PrintRule(numbers.first(), numbers.last())
                }
                val instructions: Grid<Int> = instructionsInput.map { it.split(",").toIntList() }
                return PrintRules(rules, instructions)
            }
        }
    }

    data class PrintRule(val first: Int, val second: Int) {
        val regex = """$first,.*$second""".toRegex()
        val numbers = listOf(first, second)
    }
}
