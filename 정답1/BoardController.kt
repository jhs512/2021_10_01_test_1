class BoardController {

    fun add() {

        if(logonMember == null){
            println("로그인 후 이용하여 주시기 바랍니다.")
            return
        }

        print("게시판 이름 :: ")
        val name = readLineTrim()

        val boardByName = boardRepository.getBoardByName(name)

        if(boardByName != null){
            println("게시판 이름이 중복되었습니다.")
            return
        }

        print("게시판 코드 :: ")
        val code = readLineTrim()
        val boardByCode = boardRepository.getBoardByCode(code)

        if(boardByCode != null){
            println("게시판 코드가 중복되었습니다.")
            return
        }

        val memIndex = logonMember!!.memIndex

        val boardIndex = boardRepository.addBoard(name,code,memIndex)

        println("$boardIndex 번 째 게시물이 생성되었습니다.")

    }

    fun delete(rq: Rq) {

        if(logonMember == null){
            println("로그인 후 이용하여 주시기 바랍니다.")
            return
        }

        val id = rq.getIntParam("id", 0)

        if(id == 0){
            println("게시판 번호를 확인하여 주시기 바랍니다")
            return
        }

        val board = boardRepository.getBoardByIndex(id)

        if( board == null ){
            println("존재하지 않는 게시판 입니다.")
            return
        }

        if( board.memIndex != logonMember!!.memIndex){
            println("권한이 업습니다.")
            return
        }

        boardRepository.delBoard(board)

        println("게시판이 성공적으로 삭제되었습니다.")

    }

    fun modify(rq: Rq) {

        if(logonMember == null){
            println("로그인 후 이용하여 주시기 바랍니다.")
            return
        }

        val id = rq.getIntParam("id", 0)

        if(id == 0){
            println("게시판 번호를 확인하여 주시기 바랍니다")
            return
        }

        val board = boardRepository.getBoardByIndex(id)

        if( board == null ){
            println("존재하지 않는 게시판 입니다.")
            return
        }

        if( board.memIndex != logonMember!!.memIndex){
            println("권한이 업습니다.")
            return
        }

        print("새 게시판 이름 :: ")
        board.name = readLineTrim()
        print("새 게시판 이름 :: ")
        board.name = readLineTrim()
        board.updateDate = util.getNowDateStr()

        println("게시판이 성공적으로 수정되었습니다.")
    }

    fun list(rq: Rq) {

        if(logonMember == null){
            println("로그인 후 이용하여 주시기 바랍니다.")
            return
        }

        val page = rq.getIntParam("page", 0)

        if(page == 0){
            println("페이지를 확인하여 주시기 바랍니다")
            return
        }

        val boards = boardRepository.getBoard()

        println("  번호  /  이름  /  코드  /  갱신일")

        for (board in boards){
            println("  ${board.boardIndex}  /  ${board.name}  /  ${board.code}  /  ${board.updateDate}")
        }

    }


}