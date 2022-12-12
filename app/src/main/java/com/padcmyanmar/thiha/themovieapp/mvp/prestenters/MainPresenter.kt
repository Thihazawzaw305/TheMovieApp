package com.padcmyanmar.thiha.themovieapp.mvp.prestenters

import com.padcmyanmar.thiha.themovieapp.delegates.BannerViewHolderDelegate
import com.padcmyanmar.thiha.themovieapp.delegates.MovieViewHolderDelegate
import com.padcmyanmar.thiha.themovieapp.delegates.ShowcaseViewHolderDelegate
import com.padcmyanmar.thiha.themovieapp.mvp.views.MainView

interface MainPresenter : IBasePresenter, BannerViewHolderDelegate, ShowcaseViewHolderDelegate, MovieViewHolderDelegate{

    fun initView(view: MainView)
    fun onTapGenre(genrePosition : Int)
}