class BoardRepository {

    var lastBoardIndex = 0
    val boards = mutableListOf<Board>()

    fun addBoard(name: String, code: String, memIndex: Int): Int {

        val boardIndex = ++lastBoardIndex

        val regDate = util.getNowDateStr()
        val updateDate = util.getNowDateStr()

        boards.add(Board(boardIndex,name, code, memIndex, regDate, updateDate))

        return boardIndex
    }

    fun getBoardByName(name: String): Board? {

        for ( board in boards){
            if(board.name == name){
                return board
            }
        }
        return null
    }

    fun getBoardByCode(code: String): Board? {

        for ( board in boards){
            if(board.code == code){
                return board
            }
        }
        return null

    }

    fun getBoardByIndex(id: Int): Board? {

        for ( board in boards){
            if(board.boardIndex == id){
                return board
            }
        }
        return null

    }

    fun delBoard(board: Board) {
        boards.remove(board)
    }

    fun getBoard(): List<Board> {
        return boards
    }

    fun testBoardMake(){
        addBoard("공지","Notice",1)
        addBoard("자유","Free",1)
    }


}