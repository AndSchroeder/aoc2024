package day

import util.getDiagonals
import util.rotate


object Day04 : Day("04", "18", "9") {
    override fun examplePartOne() = getExampleList().getLines().countXmas()
    override fun examplePartTwo() = getExampleList().countCrossMas()
    override fun solvePartOne() = getInputList().getLines().countXmas()
    override fun solvePartTwo() = getInputList().countCrossMas()


    private fun List<String>.countCrossMas() = flatMapIndexed { x, row ->
        row.mapIndexed { y, c ->
            if (c == 'A' && x > 0 && x < this.size - 1 && y > 0 && y < this.first().length - 1) {
                val pairTLBR = listOf(this[x - 1][y - 1], this[x + 1][y + 1])
                val pairTRBL = listOf(this[x - 1][y + 1], this[x + 1][y - 1])
                pairTRBL.validMas() && pairTLBR.validMas()
            } else false
        }
    }.count { it }.toString()

    private fun List<Char>.validMas() = contains('M') && contains('S')


    private fun List<String>.getLines(): MutableList<String> {
        val rows = this.toMutableList()
        this.forEach { row -> rows.add(row.reversed()) }

        val rotatedRows = this.rotate()
        rows.addAll(rotatedRows)
        rotatedRows.forEach { row -> rows.add(row.reversed()) }

        rows.addAll(this.getDiagonals())
        return rows
    }

    private fun List<String>.countXmas() = sumOf { "XMAS".toRegex().findAll(it).count() }.toString()
}
