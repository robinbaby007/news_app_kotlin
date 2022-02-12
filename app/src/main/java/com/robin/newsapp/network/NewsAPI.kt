package com.robin.newsapp.network

import com.robin.newsapp.Models.NewsResp
import com.robin.newsapp.Util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

//    fc2731568b9041e08da05e3229298af9
//    GET https://newsapi.org/v2/everything?q=Apple&from=2022-02-07&sortBy=popularity&apiKey=API_KEY
//    GET https://newsapi.org/v2/top-headlines?country=us&apiKey=fc2731568b9041e08da05e3229298af9


    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country") countryCode:String="in",
        @Query("page") pageNumber:Int=1,
        @Query("apiKey") apiKey: String =API_KEY
    ):Response<NewsResp>

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q") searchQuery:String,
        @Query("page") pageNumber:Int=1,
        @Query("apiKey") api:String=API_KEY
    ):Response<NewsResp>


}