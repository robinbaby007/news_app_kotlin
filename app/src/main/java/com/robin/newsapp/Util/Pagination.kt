package com.robin.newsapp.Util

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.robin.newsapp.Models.Article
import com.robin.newsapp.Models.NewsResp
import com.robin.newsapp.network.NewsAPI
import com.robin.newsapp.network.RetrofitInstance
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class Pagination:PagingSource<Int, Article>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val currentPage = params.key ?: 1
        return try {
            val response = RetrofitInstance.api.getBreakingNews("in", currentPage)
            val articleList = response.body()!!.articles
            LoadResult.Page(
                data = articleList,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (articleList.isEmpty()) null else currentPage + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
       return state.anchorPosition
    }
}