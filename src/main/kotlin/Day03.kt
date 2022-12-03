import java.io.File
import java.net.URL

fun main(args: Array<String>) {
   // solutionThreePartTwo()
    //solutionOptimized()
    println(solutionWithSets())
}
fun solutionWithSets() : Int{
    val input = getResourceAsText("03/test.txt")
    val lower = 'a'..'z'
    val upper = 'A'..'Z'
    val priorities = lower.associateWith { it.code - 96 } + upper.associateWith { it.code - 38 }

    return input.lines().sumOf {
        val (a,b) = it.chunked(it.length/2)
            .map(String::toSet)
        priorities.getOrDefault(a.intersect(b).first(),0)
    }
}
fun solutionOptimized() {
    val input = getResourceAsText("03/test.txt")
    val lower = 'a'..'z'
    val upper = 'A'..'Z'
    val priorities = lower.associateWith { it.code - 96 } + upper.associateWith { it.code - 38 }
    var values = 0

    println(
        input.lines()
        .chunked(3)
        .map { (a,b,c) -> a.first{ it in b && it in c} }
        .sumOf { priorities.getOrDefault(it,0) }
    )

}
fun solutionThreePartTwo() {
    val uri = getResourceAsURL("03/input.txt")?.toURI()
    val priorities = ('a'..'z').associateWith { it.code - 96 } + ('A'..'Z').associateWith { it.code - 38 }
    if (uri != null) {
        val inputStream = File(uri).inputStream()
        var values = 0
        var groupCounter = 0
        var lines: Array<String> = arrayOf("a", "b", "c")
        inputStream.bufferedReader().forEachLine {
            if (groupCounter == 3) {
                groupCounter = 0
            }
            lines[groupCounter] = it
            if(groupCounter == 2)
            {
                values += priorities.getOrDefault(lines[0]
                    .filter { lines[1].contains(it) }
                    .filter { lines[2].contains(it) }
                    .toCharArray().distinct()[0],0)
            }
            if(groupCounter < 3) groupCounter++
        }
        inputStream.close()
        print(values)
    }
}
fun solutionThreePartOne() {
    val uri = getResourceAsURL("03/input.txt")?.toURI()
    val lower = 'a' .. 'z'
    val upper = 'A' .. 'Z'
    val priorities = lower.associateWith { it.code - 96 } + upper.associateWith { it.code - 38 }
    if (uri != null) {
        val inputStream = File(uri).inputStream()
        var values = 0

        inputStream.bufferedReader().forEachLine {
            val c1 = it.subSequence(0 until it.length/2)
            val dupItem: Char = it.substring(it.length/2).first{ it in c1 }
            values += priorities.getOrDefault(dupItem, 0)
        }
        inputStream.close()
        print(values)
    }
}
