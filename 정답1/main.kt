val util = Util()
val membersRepository = MembersRepository()
val articleRepository = ArticleRepository()
val boardRepository = BoardRepository()

var logonMember :Member? = null

fun main() {

    val systemController = SystemController()
    val membersController = MemberController()
    val articleController = ArticleController()
    val boardController = BoardController()

    membersRepository.testMemberMake()
    boardRepository.testBoardMake()
    articleRepository.testArticleMake()


    println(" ==  Simple SSG 시작 == ")

    while(true) {

        val prompt = if(logonMember == null ){
            "명령어) "
        } else {
            "${logonMember!!.memNick}) "
        }

        print(prompt)
        val command = readLineTrim()

        val rq = Rq(command)

        when (rq.actionPath) {

            "/system/exit" -> {
                systemController.exit()
                break
            }

            "/member/join" -> {
                membersController.join()
            }

            "/member/login" -> {
                membersController.login()
            }

            "/member/logout" -> {
                membersController.logout()
            }

            "/article/write" -> {
                articleController.write()
            }

            "/article/delete" -> {
                articleController.delete(rq)
            }

            "/article/modify" -> {
                articleController.modify(rq)
            }

            "/article/detail" -> {
                articleController.detail(rq)
            }

            "/article/list" -> {
                articleController.list(rq)
            }

            "/board/add" -> {
                boardController.add()
            }

            "/board/delete" -> {
                boardController.delete(rq)
            }

            "/board/modify" -> {
                boardController.modify(rq)
            }

            "/board/list" -> {
                boardController.list(rq)
            }


        }

    }


    println(" ==  Simple SSG 시작 == ")


}