package `2022`

import getResourceAsURL
import java.io.File

fun main(args: Array<String>) {
partOne()
}

fun partTwo() {
    val uri = getResourceAsURL("2022/02/input.txt")?.toURI()
    if (uri != null) {
        val inputStream = File(uri).inputStream()
        var score = 0

        inputStream.bufferedReader().forEachLine {
            val roundScore = calcScorePart2(it.split(" ")[0].first(), it.split(" ")[1].first())
            score += roundScore
            println("Round score $roundScore")
            println("Total score $score")
        }
        inputStream.close()
    }
}

fun partOne() {
    val uri = getResourceAsURL("2022/02/input.txt")?.toURI()
    if (uri != null) {
        val inputStream = File(uri).inputStream()
        var score = 0

        inputStream.bufferedReader().forEachLine {
            val roundScore = calcScoreMap(it.split(" ")[0].first(), it.split(" ")[1].first())
            score += roundScore
            println("Round score $roundScore")
            println("Total score $score")
        }
        inputStream.close()
    }
}
fun calcScorePart2(them: Char,outcome: Char):Int {
    // Rock =  A 1
    // Paper =  B 2
    // Scissors =  C 3
    // Lose = X
    // Draw = Y
    // Win = Z
    var rock = 1
    var paper = 2
    var scissors = 3
    var score = 0
    when (outcome){
        'Y' -> score += 3
        'Z' -> score += 6
    }
    when (them) {
        'A' -> {
            when (outcome) {
                'Z' -> score += paper
                'Y' -> score += rock
                'X' -> score += scissors
            }
        }
        'B' -> {
            when (outcome) {
                'Z' -> score += scissors
                'Y' -> score += paper
                'X' -> score += rock
            }
        }
        'C' -> {
            when (outcome) {
                'Z' -> score += rock
                'Y' -> score += scissors
                'X' -> score += paper
            }
        }
    }
    return score
}
fun calcScoreMap(them: Char, us: Char) :Int {
    // Rock = X, A
    // Paper = Y, B
    // Scissors = Z, C
    val score = "XYZ".indexOf(us) + 1
    return score + when (us to them){
        'X' to 'C', 'Y' to 'A', 'Z' to 'B' -> 6
        'X' to 'A', 'Y' to 'B', 'Z' to 'C' -> 3
        else -> 0
    }
}
    fun calcScorePart1(them: Char, us: Char) :Int {
        // Rock = X, A
        // Paper = Y, B
        // Scissors = Z, C
        var score = 0
        when (us) {
            'X' -> { //rock
                score += 1
                if(them == 'C') score += 6
                else if(them == 'A') score += 3
            }
            'Y' -> { //Paper
                score += 2
                if(them == 'A') score += 6
                else if(them == 'B') score += 3
            }
            'Z' -> { //Scis
                score += 3
                if(them == 'B') score += 6
                else if(them == 'C') score += 3
            }
        }
        return score
}




