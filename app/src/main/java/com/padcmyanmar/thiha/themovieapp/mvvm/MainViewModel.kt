package com.padcmyanmar.thiha.themovieapp.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.padcmyanmar.thiha.themovieapp.data.vos.Vos.ActorVO
import com.padcmyanmar.thiha.themovieapp.data.vos.Vos.GenreVO
import com.padcmyanmar.thiha.themovieapp.data.vos.Vos.MovieVO
import com.padcmyanmar.thiha.themovieapp.data.vos.models.MovieModelImpl
import java.text.FieldPosition

class MainViewModel: ViewModel() {
    // model
    private val mMovieModel = MovieModelImpl

    // live data
    var nowPlayingMovieLiveData : LiveData<List<MovieVO>>? = null
    var popularMovieLiveData : LiveData<List<MovieVO>>? = null
    var topRatedMovieLiveData : LiveData<List<MovieVO>>? = null
    val genresLiveData = MutableLiveData<List<GenreVO>>()
    val moviesByGenreLiveData = MutableLiveData<List<MovieVO>>()
    val actorsLiveData = MutableLiveData<List<ActorVO>>()
    val mErrorLiveData = MutableLiveData<String>()

    fun getInitialData(){
        nowPlayingMovieLiveData = mMovieModel.getNowPlayingMovies { mErrorLiveData.postValue(it) }
        popularMovieLiveData = mMovieModel.getPopularMovies { mErrorLiveData.postValue(it) }
        topRatedMovieLiveData = mMovieModel.getTopRatedMovies { mErrorLiveData.postValue(it) }

        mMovieModel.getGenres(
            onSuccess = {
                genresLiveData.postValue(it)
                getMovieByGenre(0)
            },
            onFailure = {
                mErrorLiveData.postValue(it)
            }
        )

        mMovieModel.getActors(
            onSuccess = {
                actorsLiveData.postValue(it)
            },
            onFailure = {
                mErrorLiveData.postValue(it)
            }
        )
    }
fun getMovieByGenre(genrePosition: Int){
    Log.d("genres","${genresLiveData.value}")
    genresLiveData.value?.getOrNull(genrePosition)?.id?.let {
        mMovieModel.getMoviesByGenre(it.toString(), onSuccess = {moviesByGenre ->
            moviesByGenreLiveData.postValue(moviesByGenre)
        }, onFailure = {errorMessage ->
            mErrorLiveData.postValue(errorMessage)
        })
    }
}

}