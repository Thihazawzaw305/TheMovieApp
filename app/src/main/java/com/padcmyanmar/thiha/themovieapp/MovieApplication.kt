package com.padcmyanmar.thiha.themovieapp

import android.app.Application
import com.padcmyanmar.thiha.themovieapp.data.vos.models.MovieModelImpl

class MovieApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        MovieModelImpl.initDatabase(applicationContext)

    }
}