class ArticleController {
    fun write() {

        if(logonMember == null){
            println("로그인 후 이용하여 주시기 바랍니다.")
            return
        }


        print("제목 :: ")
        val title = readLineTrim()
        print("내용 :: ")
        val body = readLineTrim()

        print("게시판 번호 :: ")
        val boardIndex = readLineTrim().toInt()

        val boards = boardRepository.getBoard()

        if( boardIndex - 1 > boards.lastIndex){
            println("없는 게시판 입니다.")
            return
        }


        val memIndex = logonMember!!.memIndex

        val id = articleRepository.addArticle(title,body,memIndex,boardIndex)

        println("$id 번 게시글이 작성되었습니다.")

    }

    fun delete(rq:Rq) {

        if(logonMember == null){
            println("로그인 후 이용하여 주시기 바랍니다.")
            return
        }

        // del?id

        val id = rq.getIntParam("id",0)

        if( id == 0){
            println("게시글 번호를 확인하여 주시기 바랍니다.")
            return
        }

        val article = articleRepository.getArticleById(id)

        if (article == null){
            println("존재하지 않는 게시물 입니다.")
            return
        }

        if(logonMember!!.memIndex != article.memIndex){
            println("권한이 없습니다")
            return
        }

        articleRepository.delArticle(article)

        println("성공적으로 삭제되었습니다.")

    }

    fun modify(rq: Rq) {

        if(logonMember == null){
            println("로그인 후 이용하여 주시기 바랍니다.")
            return
        }

        // mod?id

        val id = rq.getIntParam("id",0)

        if( id == 0){
            println("게시글 번호를 확인하여 주시기 바랍니다.")
            return
        }

        val article = articleRepository.getArticleById(id)

        if (article == null){
            println("존재하지 않는 게시물 입니다.")
            return
        }

        if(logonMember!!.memIndex != article.memIndex){
            println("권한이 없습니다")
            return
        }

        print("새 제목 :: ")
        article.title = readLineTrim()
        print("새 제목 :: ")
        article.title = readLineTrim()
        article.updateDate = util.getNowDateStr()

        println("성공적으로 수정되었습니다. ")

    }

    fun detail(rq: Rq) {


        if(logonMember == null){
            println("로그인 후 이용하여 주시기 바랍니다.")
            return
        }

        // det?id

        val id = rq.getIntParam("id",0)

        if( id == 0){
            println("게시글 번호를 확인하여 주시기 바랍니다.")
            return
        }

        val article = articleRepository.getArticleById(id)

        if (article == null){
            println("존재하지 않는 게시물 입니다.")
            return
        }

        val member = membersRepository.getMemberByMemIndex(article.memIndex)!!
        val memberName = member.memNick

        val board = boardRepository.getBoardByIndex(article.boardIndex)!!
        val boardName = board.name

        println("게시판 이름 : $boardName")
        println("게시글 번호 : ${article.id}")
        println("게시글 제목 : ${article.title}")
        println("게시글 내용 : ${article.body}")
        println("게시글 작성자 : $memberName")
        println("게시글 작성일 : ${article.regDate}")
        println("게시글 갱신일 : ${article.updateDate}")

    }

    fun list(rq: Rq) {

        if(logonMember == null){
            println("로그인 후 이용하여 주시기 바랍니다.")
            return
        }

        // list?key/page

        val searchKeyword = rq.getStringParam("searchKeyword","")

        val boardId = rq.getIntParam("boardId",0)

        if(boardId == 0){
            println("게시판 번호를 확인하여 주시기 바랍니다.")
            return
        }


        val page = rq.getIntParam("page",0)

        if(page == 0){
            println("페이지를 확인하여 주시기 바랍니다")
            return
        }



        val filteredArticles = articleRepository.getFilteredArticle(boardId,searchKeyword,page,10)

        println("  게시판 이름  /  번호  /  제목  /  내용  /  작성인  /  갱신일  ")

        for (article in filteredArticles){
            val writer  = membersRepository.getMemberByMemIndex(article.memIndex)!!
            val writerName = writer.memNick

            val board = boardRepository.getBoardByIndex(article.boardIndex)!!
            val boardName = board.name

            println("  $boardName  /  ${article.id}  /  ${article.title}  /  ${article.body}  /  $writerName  /  ${article.updateDate}")
        }

    }


}