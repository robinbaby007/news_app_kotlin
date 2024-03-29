package com.robin.newsapp.ui.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.google.gson.Gson
import com.robin.newsapp.Models.Article
import com.robin.newsapp.Models.NewsResp
import com.robin.newsapp.Repository.NewsRepository
import com.robin.newsapp.Util.Resource
import com.robin.newsapp.network.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    val repository: NewsRepository
):ViewModel() {

//    val breakingNews: MutableLiveData<Resource<NewsResp>> = MutableLiveData()
    lateinit var breakingNews : LiveData<PagingData<Article>>
    val searchNews:MutableLiveData<Resource<NewsResp>> =MutableLiveData()
//    val breakingNewsPage = 1
    val searchPage=1

    fun getBreadingNews() = viewModelScope.launch {
//        breakingNews.postValue(Resource.Loading())
//        Log.e( "getBreadingNews: ",Gson().toJson(breakingNews) )
//        val response = repository.getBreadingNews(countryCode, breakingNewsPage).value
//        breakingNews.postValue(handleBreakingNews(response))
          breakingNews = repository.getBreadingNews()

    }

    fun searchNews(query:String)=viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val response=repository.searchNews(query,searchPage)
        searchNews.postValue(handleSearchNews(response))
    }

    private fun handleSearchNews(response: Response<NewsResp>):Resource<NewsResp>{

        if (response.isSuccessful){
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Failure(response.message())

    }

    private fun handleBreakingNews(resp: Response<NewsResp>): Resource<NewsResp> {
        if (resp.isSuccessful) {
            resp.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Failure(resp.message())
    }

    fun saveArticle(article: Article)=viewModelScope.launch {
        repository.upsertArticle(article)
    }

    fun getAllSavedArticles()=repository.getSavedArticles()

    fun deleteArticle(article: Article)=viewModelScope.launch {
        repository.deleteArticle(article)
    }

}