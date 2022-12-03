import java.net.URL

fun getResourceAsText(path: String): String = object {}.javaClass.getResource(path)!!.readText()

fun getResourceAsURL(path: String): URL? = object {}.javaClass.getResource(path)