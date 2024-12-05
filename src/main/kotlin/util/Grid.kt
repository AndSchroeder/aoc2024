package util


fun List<String>.rotate(): MutableList<String> {
    val list = mutableListOf<String>()
    val numRow = this.size
    val numCol = this.first().length
    (0..<numCol).forEach { y ->
        var row = ""
        (0..<numRow).forEach { x ->
            row += this[x][y]
        }
        list.add(row.reversed())
    }
    return list
}

fun List<String>.getDiagonals(): List<String> {
    return getDiagonalsTLBR() + rotate().getDiagonalsTLBR()

}

fun List<String>.getDiagonalsTLBR(): List<String> {
    val output = mutableListOf<String>()
    val numCols = this.first().length
    val numRows = this.size
    (0..<numRows).forEach { startRow ->
        var rowIndex = startRow
        var colIndex = 0
        var diagonal = ""
        while (colIndex < numCols && rowIndex < numRows) {
            diagonal += this[rowIndex][colIndex]
            rowIndex += 1
            colIndex += 1
        }
        output.add(diagonal)
        output.add(diagonal.reversed())
    }
    (1..<numCols).forEach { startCol ->
        var rowIndex = 0
        var colIndex = startCol
        var diagonal = ""
        while (colIndex < numCols && rowIndex < numRows) {
            diagonal += this[rowIndex][colIndex]
            rowIndex += 1
            colIndex += 1
        }
        output.add(diagonal)
        output.add(diagonal.reversed())
    }
    return output

}

typealias Grid<K> = List<List<K>>
