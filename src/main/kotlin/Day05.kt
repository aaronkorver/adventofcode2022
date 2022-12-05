import java.util.LinkedList

fun main(args: Array<String>) {
    val (crates,instructions) = getResourceAsText("05/input.txt").split("\n\n")
    println(crates)
    val piles = createCrateStructure(crates)
    //parseInstructionsPartOne(instructions,piles)
    parseInstructionsPartTwo(instructions,piles)
    print(piles.values.fold("") {acc, strings -> acc + strings[0] })
}

fun parseInstructionsPartOne(instructions:String, piles: MutableMap<Int,MutableList<String>>) {
    instructions.lines().forEach { instruction ->
        val match = Regex("move (\\d+) from (\\d) to (\\d)").find(instruction)!!
        val (count, from, to) = match.destructured.toList().map { it.toInt() }
        repeat(count) {
            val crateToMove = piles[from]!![0]
            piles[from] = piles[from]!!.drop(1).toMutableList()
            piles[to]!!.add(0,crateToMove)
        }
    }
}

fun parseInstructionsPartTwo(instructions:String, piles: MutableMap<Int,MutableList<String>>) {
    instructions.lines().forEach { instruction ->
        val match = Regex("move (\\d+) from (\\d) to (\\d)").find(instruction)!!
        val (count, from, to) = match.destructured.toList().map { it.toInt() }
        val cratesToMove = piles[from]!!.take(count)
        piles[from] = piles[from]!!.drop(count).toMutableList()
        piles[to]!!.addAll(0,cratesToMove)
        println(piles)
        }
    }


fun createCrateStructure(crates: String) : MutableMap<Int,MutableList<String>> {
    val piles = mutableMapOf<Int,MutableList<String>>()
crates.lines().forEach {
    var i = 0
    var pileIndex = 1
    var line = it
    while(i < it.length) {
        val crate = line.take(3).removeSurrounding("[","]")
        if(!crate.contains(Regex("[0-9]")))
        {
            piles.getOrPut(pileIndex++) { mutableListOf() }.add(crate)
        }
        i += 4
        if(i < it.length) line = line.drop(4)
    }
}
    return piles.mapValues { cleanPile(it.value) }.toMutableMap()
}
fun cleanPile(pile: MutableList<String>) = pile.filter { it.isNotBlank() }.toMutableList()
