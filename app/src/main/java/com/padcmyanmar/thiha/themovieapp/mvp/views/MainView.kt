package com.padcmyanmar.thiha.themovieapp.mvp.views


import com.padcmyanmar.thiha.themovieapp.data.vos.Vos.ActorVO
import com.padcmyanmar.thiha.themovieapp.data.vos.Vos.GenreVO
import com.padcmyanmar.thiha.themovieapp.data.vos.Vos.MovieVO

interface MainView : BaseView{
    fun showNowPlayingMovies(nowPlayingMovies: List<MovieVO>)
    fun showPopularMovies(popularMovies: List<MovieVO>)
    fun showTopRatedMovies(topRatedMovies: List<MovieVO>)
    fun showGenres(genreList: List<GenreVO>)
    fun showMoviesByGenre(moviesByGenre: List<MovieVO>)
    fun showActor(actors : List<ActorVO>)
    fun navigateToMovieDetailsScreen(movieId : Int)

}