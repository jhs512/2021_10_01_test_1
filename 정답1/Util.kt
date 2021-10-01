import java.text.SimpleDateFormat

fun readLineTrim() = readLine()!!.trim()

class Util {
    fun getNowDateStr(): String {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

        return format.format(System.currentTimeMillis())
    }
}