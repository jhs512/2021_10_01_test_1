val toolKit = ToolKit()

fun main() {

    println("캐릭터 갯수를 입력하여 주십시오 :: ")
    val repeatNum = readLineTrim().toInt()



    println("캐릭터 이름 / 태어난 해 / 직업 을 적어주세요")
    for ( i in 0 .. repeatNum){
        val chaInfo = readLineTrim()
        val splitChaInfo = chaInfo.split(" ")
        toolKit.characters.add(splitChaInfo.toString())
    }


    println("힘 / 지능 / 지능 순으로 능력치를 적어주세요")
    for ( i in 0 .. repeatNum){
        val abilInfo = readLineTrim()
        val splitAbilInfo = abilInfo.split(" ")
        toolKit.abilites.add(splitAbilInfo.toString())
    }

    toolKit.addInfo()

    println(" ==자기소개 시작==")

    println(" ==자기소개 ==")


}
