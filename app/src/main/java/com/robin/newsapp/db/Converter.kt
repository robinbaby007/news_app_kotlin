package com.robin.newsapp.db

import androidx.room.TypeConverter
import com.robin.newsapp.Models.Source

class Converter {

    @TypeConverter
    fun fromSource(source: Source):String?{
    return source.name
    }

    @TypeConverter
    fun toSource(name:String):Source{
        return Source(name,name)
    }
}