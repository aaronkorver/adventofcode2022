import java.io.File
import java.net.URI
import java.net.URL

fun main(args: Array<String>) {
solutionOne()
solutionTwo()
}
fun solutionTwo() {
    val data :Int = getResourceAsText("one/input.txt")
        .split("\n\n")
        .maxOf { it.split("\n").sumOf(String::toInt) }
    println(data)

    val data2 = getResourceAsText("one/input.txt")
        .split("\n\n")
        .map { it.split("\n").sumOf(String::toInt) }
        .sortedDescending()
        .take(3).sum()

    println(data2)
}
fun solutionOne() {
    val uri = getResourceAsURL("one/input.txt")?.toURI()
    val elfTotals = mutableListOf<Int>()
    if (uri != null) {
        val inputStream = File(uri).inputStream()
        var currentCals = 0

        inputStream.bufferedReader().forEachLine {
            if(it.isBlank()) {
                println("Elf total $currentCals ,New Elf")
                elfTotals.add(currentCals)
                currentCals = 0
            }
            else {
                currentCals += it.toInt()
            }
        }
        inputStream.close()
    }

    println("********* The max calories are ${elfTotals.max()} *********")
    print("Top three elves total is ")
    elfTotals.sortDescending()
    println(elfTotals.take(3).sum())
}

fun getResourceAsText(path: String): String = object {}.javaClass.getResource(path)!!.readText()

fun getResourceAsURL(path: String): URL? = object {}.javaClass.getResource(path)