package com.padcmyanmar.thiha.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.thiha.themovieapp.data.vos.Vos.SpokenLanguageVO

class SpokenLanguageTypeConverter {
    @TypeConverter
    fun toString(spokenLanguages : List<SpokenLanguageVO>?): String{
        return Gson().toJson(spokenLanguages)
    }
    @TypeConverter
    fun spokenLanguages(spokenLanguagesJsonString: String): List<SpokenLanguageVO>?
    {
        val spokenLanguagesListType = object : TypeToken<List<SpokenLanguageVO>?>(){}.type
        return Gson().fromJson(spokenLanguagesJsonString,spokenLanguagesListType)
    }
}