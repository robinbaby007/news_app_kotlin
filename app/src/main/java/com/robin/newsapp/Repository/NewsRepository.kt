package com.robin.newsapp.Repository

import com.robin.newsapp.db.ArticleDataBase
import com.robin.newsapp.network.RetrofitInstance

class NewsRepository(
    val db:ArticleDataBase
) {

    suspend fun getBreadingNews(countryCode:String,page:Int)= RetrofitInstance.api.getBreakingNews(countryCode,page)
    suspend fun searchNews(query:String,page: Int)=RetrofitInstance.api.searchForNews(query,page)

}