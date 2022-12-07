import java.io.File

class TreeNode(private var value: String){
    var parent:TreeNode? = null

    var children:MutableList<TreeNode> = mutableListOf()
    var totalSize: Long = 0
    var fileCount = 0

    fun addChild(node:TreeNode){
        children.add(node)
        node.parent = this
    }
    fun addFile(size: Long) {
        totalSize += size
        fileCount ++
    }
    override fun toString(): String {
        var s = "$value [${getFinalSize()}] "
//        if (!children.isEmpty()) {
//            s += " \n\t{" + children.map { it.toString() } + " }"
//        }
        return s
    }
    fun toNiceString(level: Int): String {
        var s = "$value [${getFinalSize()}] "
        val tabs = "\t".repeat(level)
        if (children.isNotEmpty()) {
            s += "\n"
            s += " {" + children.map { tabs + it.toNiceString(level + 1) } + " }"
        }
        return s
    }

    fun getFinalSize():Long = totalSize + children.sumOf { it.getFinalSize() }

    fun nodeLessThan(size: Long): List<TreeNode>{
        val l = mutableListOf<TreeNode>()
        if(this.getFinalSize() <= size)
        {
            l.add(this)
        }
        if(children.isNotEmpty())
        {
           l.addAll(children.flatMap{ it.nodeLessThan(size)})
        }
        return l
    }

    fun getNode(name: String): TreeNode = children.first { it.value == name  }

    fun forEachDepthFirst(visit: Visitor) {
        visit.visit(this)
        children.forEach {
            it.forEachDepthFirst(visit)
        }
    }

}

class Visitor (val size: Long) {
    val l = mutableListOf<TreeNode>()
    fun visit(node: TreeNode){
        if(node.getFinalSize() >= size) l.add(node)
    }
}
fun main(args: Array<String>) {
    val root = TreeNode("/")
    var currentNode = root
    val uri = getResourceAsURL("07/input.txt")?.toURI()
    if (uri != null) {
        File(uri).inputStream().bufferedReader().forEachLine {
           currentNode = parseLine(it,currentNode)
        }
    }
    val freeSpace = 70000000 - root.getFinalSize()
    val neededSpace = 30000000 - freeSpace
    println(neededSpace)
    val v = Visitor(neededSpace)
    root.forEachDepthFirst(v)
    //part one answer
    //println(root.nodeLessThan(100000).sumOf { it.getFinalSize() })
    v.l.sortBy { it.getFinalSize() }
    println(v.l.first())
}

fun parseLine(line:String, node: TreeNode): TreeNode {
    //println(line)
    if (line == "$ ls") return node

    val (first,second) = line.trimStart('$',' ').split(" ")
    return if(first == "dir"){
        node.addChild(TreeNode(second))
        node
    }
    else if (first.toIntOrNull() != null) {
        node.addFile(first.toLong())
        node
    }
    else if (first == "cd"){
        if(second == "..") node.parent!!
        else node.getNode(second)
    }
    else node
}