package com.padcmyanmar.thiha.themovieapp.mvp.prestenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.thiha.themovieapp.data.vos.models.MovieModelImpl
import com.padcmyanmar.thiha.themovieapp.interactors.MovieInteractor
import com.padcmyanmar.thiha.themovieapp.interactors.MovieinteractorImpl
import com.padcmyanmar.thiha.themovieapp.mvp.views.MovieDetailsView

class MovieDetailsPresenterImpl: ViewModel(), MovieDetailsPresenter {

    // Interactor
    private val mMovieInteractor : MovieInteractor = MovieinteractorImpl
    // View
    private var mView: MovieDetailsView? = null


    override fun initView(view: MovieDetailsView) {
        mView = view
    }

    override fun onUiReadyInMovieDetails(owner: LifecycleOwner, movieId: Int) {
    // Movie Details
        mMovieInteractor.getMovieDetails(movieId.toString()){
            mView?.showError(it)
        }?.observe(owner){
            it?.let {
                mView?.showMovieDetails(it)
            }
        }
     // Credit

        mMovieInteractor.getCreditsByMovie(movieId = movieId.toString(), onSuccess = {
         mView?.showCreditsByMovie(cast = it.first, crew = it.second)
     }, onFailure = {
         mView?.showError(it)
     })
    }

    override fun onTapBack() {
      mView?.navigateBack()
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }
}