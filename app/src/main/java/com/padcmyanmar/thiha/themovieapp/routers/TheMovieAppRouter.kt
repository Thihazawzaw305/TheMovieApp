package com.padcmyanmar.thiha.themovieapp.routers

import android.app.Activity
import com.padcmyanmar.thiha.themovieapp.activities.MovieDetailsActivity
import com.padcmyanmar.thiha.themovieapp.activities.MovieSearchActivity

fun Activity.navigateToMovieDetailsActivity(movieId :Int){
    startActivity(MovieDetailsActivity.newIntent(this, movieId = movieId))
    }

fun Activity.navigateToMovieSearchActivity(){
    startActivity(MovieSearchActivity.newIntent(this))
}