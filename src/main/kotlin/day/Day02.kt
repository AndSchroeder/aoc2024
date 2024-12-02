package day

import kotlin.math.abs


object Day02 : Day("02", "2", "4") {
    override fun examplePartOne() = getExampleList().safeReportsAmount().toString()
    override fun examplePartTwo() = getExampleList().safeReportAmountAllCombinations().toString()
    override fun solvePartOne() = getInputList().safeReportsAmount().toString()
    override fun solvePartTwo() = getInputList().safeReportAmountAllCombinations().toString()

    private fun List<String>.safeReportsAmount() = map {
        it.getReportList().distances()
    }.filter { it.isOk() }.size

    private fun List<String>.safeReportAmountAllCombinations() = map {
        it.getReportList().getReportListCombinations().map { list ->
            list.distances()
        }.filter {filterItem ->  filterItem.isOk() }.size
    }.filter { it > 0 }.size

    private fun String.getReportList() = split("""\s""".toRegex())
    private fun List<String>.getReportListCombinations(): MutableList<List<String>> {
        val lists = mutableListOf(this)
        indices.forEach {
            val newList = this.toMutableList()
            newList.removeAt(it)
            lists.add(newList)
        }
        return lists
    }

    private fun List<String>.distances() = zipWithNext { first, second -> first.toInt() - second.toInt() }

    private fun List<Int>.isOk() = isDifferLevelOk() && (isDecreasing() || isIncreasing())
    private fun List<Int>.isDifferLevelOk() = all { (1..3).contains(abs(it)) }

    private fun List<Int>.isDecreasing() = all { it < 0 }
    private fun List<Int>.isIncreasing() = all { it > 0 }
}
