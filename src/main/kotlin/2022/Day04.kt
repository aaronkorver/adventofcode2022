package `2022`

import getResourceAsText
import getResourceAsURL
import java.io.File

fun main(args: Array<String>) {
    solutionFourPartTwo()
}

fun solutionFourPartOne() {
    //2-4,6-8
    val uri = getResourceAsURL("2022/04/input.txt")?.toURI()
    if (uri != null) {
        val inputStream = File(uri).inputStream()
        var count = 0
        inputStream.bufferedReader().forEachLine {
            val (a,b)  = it.split(",")
            .map {
                it.split("-")
                    .map(String::toInt)
                    .let { (a, b) -> (a..b) }
            }
            val unionSize = a.union(b).size
            if(unionSize == a.count() || unionSize == b.count()) count++
        }
        print(count)

    }
}

fun solutionFourPartTwo() {
    //2-4,6-8
    val text = getResourceAsText("2022/04/input.txt")
        val count = text.lines().count {
            val (a, b) = it.split(",")
                .map { it.split("-")
                    .map(String::toInt)
                    .let { (a,b) -> (a..b) }
                }
            a.intersect(b).isNotEmpty()
        }
        print(count)

    }
