package com.padcmyanmar.thiha.themovieapp.mvp.prestenters

import androidx.lifecycle.LifecycleOwner
import com.padcmyanmar.thiha.themovieapp.mvp.views.MovieDetailsView

interface MovieDetailsPresenter : IBasePresenter {
    fun initView(view : MovieDetailsView)
    fun onUiReadyInMovieDetails(owner: LifecycleOwner, movieId: Int)
    fun onTapBack()
}