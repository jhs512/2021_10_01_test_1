class ArticleRepository {

    var lastArticleIndex = 0
    val articles = mutableListOf<Article>()

    fun addArticle(title: String, body: String, memIndex: Int,boardIndex:Int): Int {

        val id = ++lastArticleIndex

        val regDate = util.getNowDateStr()
        val updateDate = util.getNowDateStr()

        articles.add(Article(id, title, body, regDate, updateDate, memIndex,boardIndex))

        return id
    }

    fun getArticleById(id: Int): Article? {

        for (article in articles) {
            if(article.id == id){
                return article
            }
        }
        return null
    }

    fun delArticle(article: Article) {
        articles.remove(article)
    }

    fun getFilteredArticle(boardId: Int,searchKeyword: String, page: Int, itemsInAPage: Int): List<Article> {

        val filtered1Article = getSearchKeywordFilteredArticle(articles,searchKeyword,boardId)
        val filtered2Article = getPageFilteredArticles(filtered1Article,page,itemsInAPage)

        return filtered2Article

    }

    private fun getPageFilteredArticles(filtered1Article: List<Article>, page: Int, itemsInAPage: Int): List<Article> {

        val filteredArticles = mutableListOf<Article>()

        val offsetCount  = ( page - 1 ) * itemsInAPage
        val startIndex = filtered1Article.lastIndex - offsetCount
        var endIndex = startIndex - ( itemsInAPage - 1 )

        if(endIndex < 0){
            endIndex = 0
        }

        for( i in startIndex downTo endIndex){
            filteredArticles.add(filtered1Article[i])
        }

        return filteredArticles

    }

    private fun getSearchKeywordFilteredArticle(articles: List<Article>, searchKeyword: String,boardId:Int): List<Article> {

        val filteredArticles = mutableListOf<Article>()

        for(article in articles){
            if(article.title.contains(searchKeyword) && article.boardIndex == boardId){
                filteredArticles.add(article)
            }
        }
        return filteredArticles
    }

    fun testArticleMake() {
        for ( i in 1 .. 200){
            addArticle("제목$i","내용$i",i % 9 + 1, i % 2 + 1 )
        }
    }


}