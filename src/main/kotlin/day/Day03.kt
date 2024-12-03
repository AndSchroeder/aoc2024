package day

import util.product
import util.sum
import util.toLongList


object Day03 : Day("03", "161", "48") {
    override fun examplePartOne() = getExampleRaw().getProductSum()
    override fun examplePartTwo() =
        "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))".getEnabledProductSum()

    override fun solvePartOne() = getInputRaw().getProductSum()
    override fun solvePartTwo() = getInputRaw().getEnabledProductSum()

    private const val MUL_REGEX = """(mul\(\d{1,3},\d{1,3}\))"""
    private const val INSTRUCTION_REGEX = """(mul\(\d{1,3},\d{1,3}\)|don't\(\)|do\(\))"""


    private fun String.getProductSum() = getAllMultiplications().sumOf { it.getProduct() }.toString()

    private fun String.getProduct() = this.replace("mul(", "").replace(")", "").split(",").toLongList().product()

    private fun String.getAllInstructions() = INSTRUCTION_REGEX.toRegex().findAll(this).map { it.value }.toList()

    private fun String.getAllMultiplications() = MUL_REGEX.toRegex().findAll(this).map { it.value }.toList()

    private fun String.getEnabledProductSum(): String {
        var isEnabled = true
        val list = mutableListOf<Long>()
        getAllInstructions().forEach { entry ->
            when (entry) {
                "don't()" -> isEnabled = false
                "do()" -> isEnabled = true
                else -> if (isEnabled) list.add(entry.getProduct())
            }
        }
        return list.sum().toString()
    }
}
