fun readLineTrim() = readLine()!!.trim()

class ToolKit {

    val information = mutableMapOf<String, String>()

    val characters = mutableListOf<String>()
    val abilites = mutableListOf<String>()

    fun addInfo(){
        for ( i in 0 .. 3){
            information[characters[i]] = abilites[i]
        }
    }

}