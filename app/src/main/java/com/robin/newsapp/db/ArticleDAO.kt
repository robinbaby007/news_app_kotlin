package com.robin.newsapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.robin.newsapp.Models.Article

@Dao
interface ArticleDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article):Long

    @Query("select * from articles")
     fun getAllArticles():LiveData<List<Article>>

     @Delete
     suspend fun deleteArticle(article: Article)

}