package `2022`

import getResourceAsText

fun main(args: Array<String>) {
    val windowSize = 14
    println(
        getResourceAsText("2022/06/input.txt")
        .windowed(windowSize)
        .map(String::toSet)
        .indexOfFirst { chars -> chars.size == windowSize } + windowSize)
}

