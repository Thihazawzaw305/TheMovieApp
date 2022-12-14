package com.padcmyanmar.thiha.themovieapp.mvi.viewmodels

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.padcmyanmar.thiha.themovieapp.mvi.intents.MainIntent
import com.padcmyanmar.thiha.themovieapp.mvi.mvibase.MVIViewModel
import com.padcmyanmar.thiha.themovieapp.mvi.processors.MainProcessor
import com.padcmyanmar.thiha.themovieapp.mvi.states.MainState

class MainViewModel(override var state : MutableLiveData<MainState> = MutableLiveData(MainState.idle())):
MVIViewModel<MainState, MainIntent>, ViewModel(){

    private val mProcessor = MainProcessor

    override fun processIntent(intent: MainIntent, lifeCycleOwner: LifecycleOwner) {
     when (intent){
         // Load Home Page Data
         MainIntent.LoadAllHomePageData ->{
             state.value?.let {
                 mProcessor.loadAllHomePageData(
                     previousState = it
                 ).observe(lifeCycleOwner){ newState ->
                     state.postValue(newState)
                     if (newState.moviesByGenre.isEmpty()){
                         processIntent(MainIntent.LoadMoviesByGenreIntent(0),lifeCycleOwner)
                     }

                 }
             }
         }
         // load Movie By Genre
         is MainIntent.LoadMoviesByGenreIntent ->{
             state.value?.let {
                 val genreId = it.genres.getOrNull(intent.genrePosition)?.id ?: 0
                 mProcessor.loadMoviesByGenre(
                     genreId = genreId,
                     previousState = it
                 ).observe(lifeCycleOwner, state::postValue)
             }
         }
     }
    }
}