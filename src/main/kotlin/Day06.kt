fun main(args: Array<String>) {
    val windowSize = 14
    println(getResourceAsText("06/input.txt")
        .windowed(windowSize)
        .map(String::toSet)
        .indexOfFirst { chars -> chars.size == windowSize } + windowSize)
}

