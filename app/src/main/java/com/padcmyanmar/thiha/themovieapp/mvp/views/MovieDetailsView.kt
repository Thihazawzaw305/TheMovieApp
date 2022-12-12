package com.padcmyanmar.thiha.themovieapp.mvp.views

import com.padcmyanmar.thiha.themovieapp.data.vos.Vos.ActorVO
import com.padcmyanmar.thiha.themovieapp.data.vos.Vos.MovieVO

interface MovieDetailsView {
    fun showMovieDetails(movie : MovieVO)
    fun showCreditsByMovie(cast: List<ActorVO>, crew: List<ActorVO>)
    fun navigateBack()
    fun showError(error : String)
}