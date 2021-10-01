class MemberController {

    fun join() {

        print("아이디 :: ")
        val memId = readLineTrim()

        val addableId = membersRepository.isAddableId(memId)

        if(!addableId){
            println("이미 존재하는 아이디 입니다.")
            return
        }


        print("비밀번호 :: ")
        val memPW = readLineTrim()
        print("닉네임 :: ")
        val memNick = readLineTrim()
        print("이름 :: ")
        val memName = readLineTrim()
        print("전화번호 :: ")
        val memPHNum = readLineTrim()
        print("이메일 :: ")
        val memEmail = readLineTrim()

        val memIndex = membersRepository.addMember(memId,memPW,memNick,memName,memPHNum,memEmail)

        println("${memIndex}번 째 회원님 환영합니다!")

    }

    fun login(){

        print("아이디 :: ")
        val userId = readLineTrim()

        val member = membersRepository.getMemberByMemId(userId)

        if( member == null){
            println("존재하지 않는 아이디 입니다")
            return
        }


        print("비밀번호 :: ")
        val userPW = readLineTrim()

        if (member.memPW != userPW){
            println("비밀번호가 일치하지 않습니다.")
            return
        }


        logonMember = member

        println("${member.memNick}님 환영합니다!")

    }

    fun logout() {
        logonMember = null
        println("성공적으로 로그아웃 되셨습니다.")
    }
}