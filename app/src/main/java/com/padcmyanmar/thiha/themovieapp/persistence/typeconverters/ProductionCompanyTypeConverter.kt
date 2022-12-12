package com.padcmyanmar.thiha.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.thiha.themovieapp.data.vos.Vos.ProductionCompanyVO

class ProductionCompanyTypeConverter {
    @TypeConverter
    fun toString(productionCompanies: List<ProductionCompanyVO>?): String{
        return Gson().toJson(productionCompanies)

    }
    @TypeConverter
    fun toProductionCompanies(productionComapniesJsonString: String): List<ProductionCompanyVO>?{
        val productionCompaniesListType = object :TypeToken<List<ProductionCompanyVO>?>() {}.type
        return Gson().fromJson(productionComapniesJsonString,productionCompaniesListType)
    }
}