package day

import util.toIntList
import kotlin.math.abs


object Day01 : Day("01", "11","31") {
    override fun examplePartOne() = getExampleList().getAdditionDistance()
    override fun examplePartTwo() = getExampleList().getAmountAndMultiplicationDistance()
    override fun solvePartOne() = getInputList().getAdditionDistance()
    override fun solvePartTwo() = getInputList().getAmountAndMultiplicationDistance()

    private fun List<String>.getAdditionDistance() = getLists().let { lists ->
        lists.first.zip(lists.second).sumOf { abs(it.first - it.second) }
    }.toString()

    private fun List<String>.getAmountAndMultiplicationDistance() = getLists().let { lists ->
        lists.first.sumOf { first -> first * lists.second.filter { second -> second == first  }.size }
    }.toString()

    private fun List<String>.getLists() = map { it.getPair() }.unzip().run { first.sorted().toIntList() to second.sorted().toIntList() }

    private fun String.getPair() = split("""\s""".toRegex()).run { first() to last() }
}
