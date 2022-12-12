package com.padcmyanmar.thiha.themovieapp.data.vos.models

import android.content.Context
import com.padcmyanmar.thiha.themovieapp.network.TheMovieApi
import com.padcmyanmar.thiha.themovieapp.persistence.MovieDatabase
import com.padcmyanmar.thiha.themovieapp.utils.BASE_URL
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseModel {
    protected var mMovieApi : TheMovieApi
    protected var mMovieDatabase : MovieDatabase? = null


    init {
        val mOkHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) // extra line for converter
            .build()
     mMovieApi =retrofit.create(TheMovieApi::class.java)
    }

    fun initDatabase(context: Context){
        mMovieDatabase = MovieDatabase.getDBInstance(context)
    }
}