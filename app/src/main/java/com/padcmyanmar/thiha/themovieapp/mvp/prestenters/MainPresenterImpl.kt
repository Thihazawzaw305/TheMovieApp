package com.padcmyanmar.thiha.themovieapp.mvp.prestenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.thiha.themovieapp.data.vos.Vos.GenreVO
import com.padcmyanmar.thiha.themovieapp.data.vos.models.MovieModel
import com.padcmyanmar.thiha.themovieapp.data.vos.models.MovieModelImpl
import com.padcmyanmar.thiha.themovieapp.interactors.MovieInteractor
import com.padcmyanmar.thiha.themovieapp.interactors.MovieinteractorImpl
import com.padcmyanmar.thiha.themovieapp.mvp.views.MainView

class MainPresenterImpl: ViewModel(), MainPresenter {
    // View
    var mView : MainView? = null

    // Model
//interactor
    private val mMovieInteractor : MovieInteractor = MovieinteractorImpl

    // States

    private var mGenres : List<GenreVO>? = listOf()







    override fun initView(view: MainView) {
    mView = view
    }


    override fun onUiReady(owner: LifecycleOwner) {
      // Now Playing
        mMovieInteractor.getNowPlayingMovies {
            mView?.showError(it)
        }?.observe(owner){
            mView?.showNowPlayingMovies(it)
        }

        // Popular Movies
        mMovieInteractor.getPopularMovies {
            mView?.showError(it)
        }?.observe(owner){
            mView?.showPopularMovies(it)
        }


        // Top Rated Movies
        mMovieInteractor.getTopRatedMovies {
            mView?.showError(it)
        }?.observe(owner){
            mView?.showTopRatedMovies(it)
        }

        // Genre and Get Movies For First Genre
        mMovieInteractor.getGenres(
            onSuccess = {
                mGenres = it
                mView?.showGenres(it)
                it.firstOrNull()?.id?.let { firstGenreId ->
                    onTapGenre(firstGenreId)
                }
            },
            onFailure = {
                mView?.showError(it)
            }
        )

        // Actor
        mMovieInteractor.getActors(
            onSuccess = {
                mView?.showActor(it)

            },
            onFailure = {
                mView?.showError(it)
            }
        )
    }

    override fun onTapMovieFromBanner(movieId: Int) {
      mView?.navigateToMovieDetailsScreen(movieId)
    }

    override fun onTapMovieFromShowcase(movieId: Int) {
        mView?.navigateToMovieDetailsScreen(movieId)
    }

    override fun onTapMovie(movieId: Int) {
        mView?.navigateToMovieDetailsScreen(movieId)
    }

    override fun onTapGenre(genrePosition: Int) {
   mGenres?.getOrNull(genrePosition)?.id?.let { genreId ->
       mMovieInteractor.getMoviesByGenre(genreId = genreId.toString(), onSuccess = {
           mView?.showMoviesByGenre(it)
       }, onFailure = {
           mView?.showError(it)
       }
       )
   }
    }

}