package com.padcmyanmar.thiha.themovieapp.mvi.intents

import com.padcmyanmar.thiha.themovieapp.mvi.mvibase.MVIIntent

sealed class MainIntent : MVIIntent{
    class LoadMoviesByGenreIntent(val genrePosition : Int): MainIntent()
    object LoadAllHomePageData: MainIntent()
}