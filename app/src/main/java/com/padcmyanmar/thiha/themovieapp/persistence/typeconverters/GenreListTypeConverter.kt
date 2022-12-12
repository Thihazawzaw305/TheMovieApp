package com.padcmyanmar.thiha.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.thiha.themovieapp.data.vos.Vos.GenreVO

class GenreListTypeConverter {
    @TypeConverter
    fun toString(genreList: List<GenreVO>?): String{
        return Gson().toJson(genreList)
    }
    @TypeConverter
    fun toGenreList(genreListJsonString : String): List<GenreVO>?{
        val genreListType = object : TypeToken<List<GenreVO>?>(){}.type
        return Gson().fromJson(genreListJsonString,genreListType)
    }
}