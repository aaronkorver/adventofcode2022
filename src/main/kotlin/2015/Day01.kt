package `2015`

import getResourceAsText

fun main(args: Array<String>) {

    println(getResourceAsText("2015/01/input.txt").foldRight(0){c,i ->
        when(c){
            '(' -> i+1
            ')' -> i-1
            else -> i
        }
    })

    var floor = 0
   print(getResourceAsText("2015/01/input.txt").takeWhile {c->
        floor = when(c){
            '(' -> floor+1
            ')' -> floor-1
            else -> floor
        }
        floor >= 0
    }.length + 1 )
}