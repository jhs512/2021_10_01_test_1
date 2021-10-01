class MembersRepository {

    var lastMemberIndex = 0
    val members = mutableListOf<Member>()

    fun addMember(memId: String, memPW: String, memNick: String, memName: String, memPHNum: String, memEmail: String): Int {

        val memIndex = ++lastMemberIndex

        val regDate = util.getNowDateStr()
        val updateDate = util.getNowDateStr()

        members.add(Member(memIndex, memId, memPW, memNick, memName, memPHNum, memEmail, regDate, updateDate))

        return memIndex
    }

    fun getMemberByMemId(userId: String): Member? {

        for( member in members){
            if(member.memId == userId){
                return member
            }
        }
        return null
    }

    fun isAddableId(memId: String): Boolean {

        val addableTF = getMemberByMemId(memId)

        return addableTF == null

    }

    fun getMemberByMemIndex(memIndex: Int): Member? {

        for( member in members ){
            if(member.memIndex == memIndex){
                return member
            }
        }
        return null
    }

    fun testMemberMake(){
        for (i in 1 .. 10){
            addMember("user$i","1","User$i","Name$i","010-1234-5678","abc@example.com")
        }
    }
}