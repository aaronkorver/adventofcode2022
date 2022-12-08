fun main(args: Array<String>) {

    val input = mutableListOf<MutableList<Pair<Int,String>>>()
    getResourceAsText("08/input.txt")
        .lines().forEach { tree ->
            run {
                val testList = tree.toCharArray().map { Pair(it.toString().toInt(), "") }.toMutableList()
                input.add(testList)
            }
        }
    val height = input.size
    val length = input[0].size

    for(i in 0 until height)
    {
        for(j in 0 until length) scoreVisible(input,i,j)
    }
    //println(input.sumOf { it.filter { it.second == "Y" }.size })
    println(input.maxOf { it.maxOf { it.second.toInt() } })
}

fun checkNorth(input: MutableList<MutableList<Pair<Int, String>>>, i: Int, j: Int, tree:Int) = (i-1 >= 0 && input[i-1][j].first < tree)
fun checkSouth(input: MutableList<MutableList<Pair<Int, String>>>, i: Int, j: Int, tree:Int) = (i+1 < input.size && input[i+1][j].first < tree)
fun checkEast(input: MutableList<MutableList<Pair<Int, String>>>, i: Int, j: Int, tree:Int) = (j+1 < input[0].size && input[i][j+1].first < tree)
fun checkWest(input: MutableList<MutableList<Pair<Int, String>>>, i: Int, j: Int, tree:Int) = (j-1 >= 0 && input[i][j-1].first < tree)

fun isEdge(i: Int,j: Int, input: MutableList<MutableList<Pair<Int, String>>>) = (i == 0 || j == 0 || i == input.size - 1 || j == input[0].size - 1 )


fun checkIfVisible(input: MutableList<MutableList<Pair<Int, String>>>, i: Int, j: Int) {
    val tree = input[i][j]
    if(walkNorthUntil(input,i,j,tree.first) ||
        walkSouthUntil(input,i,j,tree.first) ||
        walkWestUntil(input,i,j,tree.first) ||
        walkEastUntil(input,i,j,tree.first)
        )
    {
        input[i][j] = tree.first to "Y"
    }
}

fun scoreVisible(input: MutableList<MutableList<Pair<Int, String>>>, i: Int, j: Int) {
    val tree = input[i][j]
    input[i][j] = tree.first to (
            scoreNorthUntil(input,i,j,tree.first,1) *
            scoreSouthUntil(input,i,j,tree.first,1) *
            scoreWestUntil(input,i,j,tree.first,1) *
            scoreEastUntil(input,i,j,tree.first,1)).toString()

}

fun walkNorthUntil(input: MutableList<MutableList<Pair<Int, String>>>, i: Int, j: Int, treeVal: Int): Boolean {
    if(isEdge(i,j,input)) return true
    return if(checkNorth(input,i,j,treeVal)) walkNorthUntil(input,i-1,j,treeVal) else false
}

fun walkSouthUntil(input: MutableList<MutableList<Pair<Int, String>>>, i: Int, j: Int, treeVal: Int): Boolean {
    if(isEdge(i,j,input)) return true
    return if(checkSouth(input,i,j,treeVal)) walkSouthUntil(input,i+1,j,treeVal) else false
}

fun walkWestUntil(input: MutableList<MutableList<Pair<Int, String>>>, i: Int, j: Int, treeVal: Int): Boolean {
    if(isEdge(i,j,input)) return true
    return if(checkWest(input,i,j,treeVal)) walkWestUntil(input,i,j-1,treeVal) else false
}

fun walkEastUntil(input: MutableList<MutableList<Pair<Int, String>>>, i: Int, j: Int, treeVal: Int): Boolean {
    if(isEdge(i,j,input)) return true
    return if(checkEast(input,i,j,treeVal)) walkEastUntil(input,i,j+1,treeVal) else false
}

fun scoreNorthUntil(input: MutableList<MutableList<Pair<Int, String>>>, i: Int, j: Int, treeVal: Int, steps:Int): Int {
    if(isEdge(i,j,input)) return 0
    return if(checkNorth(input,i,j,treeVal)) 1 + scoreNorthUntil(input,i-1,j,treeVal,steps) else steps
}

fun scoreSouthUntil(input: MutableList<MutableList<Pair<Int, String>>>, i: Int, j: Int, treeVal: Int, steps:Int): Int {
    if(isEdge(i,j,input)) return 0
    return if(checkSouth(input,i,j,treeVal)) 1 + scoreSouthUntil(input,i+1,j,treeVal,steps) else steps
}

fun scoreWestUntil(input: MutableList<MutableList<Pair<Int, String>>>, i: Int, j: Int, treeVal: Int, steps:Int): Int {
    if(isEdge(i,j,input)) return 0
    return if(checkWest(input,i,j,treeVal)) 1 + scoreWestUntil(input,i,j-1,treeVal,steps) else steps
}

fun scoreEastUntil(input: MutableList<MutableList<Pair<Int, String>>>, i: Int, j: Int, treeVal: Int, steps:Int): Int {
    if(isEdge(i,j,input)) return 0
    return if(checkEast(input,i,j,treeVal)) 1 + scoreEastUntil(input,i,j+1,treeVal,steps) else steps
}

