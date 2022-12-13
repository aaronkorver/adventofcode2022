fun main(args: Array<String>) {
    day10part01()
}

fun printScreen(values: Array<String>) {
    values.forEachIndexed { index, s ->
        print(s)
        if((index+1) % 40 == 0) println()
    }
}
fun day10part02() {
    var operations = getResourceAsText("10/input.txt").lines()
    var cycles = 1
    var busy = false
    var registerX = 1
    var addx = 0
    var values :Array<String> = Array(240){" "}
    while (operations.isNotEmpty() ||(operations.isEmpty() && busy))
    {
        if((cycles % 40 ) in registerX until registerX + 3) values[cycles-1] = "#" else values[cycles-1] = "."
        when {
            busy -> {
                busy = false
                registerX += addx
            }
            operations[0].startsWith("addx") -> {
                addx = operations[0].split(" ")[1].toInt()
                busy = true
                operations = operations.drop(1)
            }
            operations[0] == "noop" -> {
                operations = operations.drop(1)
            }
        }
        cycles++

    }
    printScreen(values)
}
fun day10part01() {
    var operations = getResourceAsText("10/test.txt").lines()
    var cycles = 1
    var busy = false
    var registerX = 1
    var addx = 0
    var values :Array<Int> = Array(240){0}
    var total = 0
    while (operations.isNotEmpty() ||(operations.isEmpty() && busy))
    {
        total += when {
            cycles == 20 -> {
                println(registerX * 20)
                registerX * 20
            }
            (cycles - 20)  % 40 == 0 -> {
                println(registerX * (cycles))
                registerX * (cycles)
            }
            else -> 0
        }
        when {
            busy -> {
                busy = false
                registerX += addx
            }
            operations[0].startsWith("addx") -> {
                addx = operations[0].split(" ")[1].toInt()
                busy = true
                operations = operations.drop(1)
            }
            operations[0] == "noop" -> {
                operations = operations.drop(1)
            }
        }
        values[cycles - 1] = registerX
        cycles++
    }
    println(total)
}


