package util

fun List<Long>.sum(): Long = this.reduce { acc, number -> acc + number }
fun List<Long>.product(): Long = this.reduce { acc, number -> acc * number }
fun List<Long>.lcm() = reduce { acc, number -> lcm(acc, number) }
fun String.toStringList() = toList().map(Char::toString)
fun String.toLongList() = split("""\s""".toRegex()).map(String::toLong)
fun List<String>.toIntList() = map(String::toInt)
fun List<String>.toLongList() = map(String::toLong)

fun <T>List<T>.combinations() = this.mapIndexed { y, yValue ->
    this.mapIndexed { x, xValue ->
        if (x <= y) return@mapIndexed null
        xValue to yValue
    }.filterNotNull()
}.flatten()

fun <T, R> List<T>.zipWithNextAndIndex(transform: (index: Int, a: T, b: T) -> R): List<R> {
    return zipWithNext()
        .mapIndexed { index, (a, b) -> transform(index, a, b) }
}
fun <T> MutableList<T>.switchElements(element1: T, element2: T): Boolean {
    val index1 = this.indexOf(element1)
    val index2 = this.indexOf(element2)

    if (index1 == -1 || index2 == -1) {
        return false
    }

    this[index1] = element2
    this[index2] = element1
    return true
}