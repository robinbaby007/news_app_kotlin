package com.robin.newsapp.Repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.robin.newsapp.Models.Article
import com.robin.newsapp.Util.Pagination
import com.robin.newsapp.db.ArticleDataBase
import com.robin.newsapp.network.RetrofitInstance

class NewsRepository(
    val db:ArticleDataBase
) {

//suspend fun getBreadingNews(countryCode:String,page:Int)= RetrofitInstance.api.getBreakingNews(countryCode,page)
  fun getBreadingNews()=Pager(
    config = PagingConfig(
        pageSize = 20,
        maxSize = 100,
        enablePlaceholders = false
    ), pagingSourceFactory = {Pagination()}
).liveData

    suspend fun searchNews(query:String,page: Int)=RetrofitInstance.api.searchForNews(query,page)
    suspend fun upsertArticle(article:Article)=db.getArticleDAO().upsert(article)
    fun getSavedArticles()=db.getArticleDAO().getAllArticles()
    suspend fun deleteArticle(article: Article)=db.getArticleDAO().deleteArticle(article)


}