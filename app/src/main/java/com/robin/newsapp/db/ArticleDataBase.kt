package com.robin.newsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.robin.newsapp.Models.Article

@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(Converter::class)
abstract class ArticleDataBase : RoomDatabase() {

    abstract fun getArticleDAO():ArticleDAO

    companion object{

        @Volatile
        private  var instance:ArticleDataBase?=null
        private val lock=Any()

       operator fun invoke(context: Context)= instance?: synchronized(lock){
            instance?:createDataBase(context).also{ instance=it}
       }
        private fun createDataBase(context: Context): ArticleDataBase =

             Room.databaseBuilder(
                  context.applicationContext,
                  ArticleDataBase::class.java,
                  "article_db.db"
             ).build()


    }
}