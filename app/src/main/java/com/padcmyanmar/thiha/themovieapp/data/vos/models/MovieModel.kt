package com.padcmyanmar.thiha.themovieapp.data.vos.models

import androidx.lifecycle.LiveData
import com.padcmyanmar.thiha.themovieapp.data.vos.Vos.ActorVO
import com.padcmyanmar.thiha.themovieapp.data.vos.Vos.GenreVO
import com.padcmyanmar.thiha.themovieapp.data.vos.Vos.MovieVO
import io.reactivex.rxjava3.core.Observable

interface MovieModel {
    fun getNowPlayingMovies(
//        onSuccess : (List<MovieVO>) -> Unit,
        onFailure : (String) -> Unit
    ): LiveData<List<MovieVO>>?

    fun getPopularMovies(
//        onSuccess : (List<MovieVO>) -> Unit,
        onFailure : (String) -> Unit
    ): LiveData<List<MovieVO>>?

    fun getTopRatedMovies(
//        onSuccess : (List<MovieVO>) -> Unit,
        onFailure : (String) -> Unit
    ): LiveData<List<MovieVO>>?

    fun getGenres(
        onSuccess : (List<GenreVO>) -> Unit,
        onFailure : (String) -> Unit
    )

    fun getMoviesByGenre(
        genreId: String,
        onSuccess : (List<MovieVO>) -> Unit,
        onFailure : (String) -> Unit
    )

    fun getActors(
        onSuccess : (List<ActorVO>) -> Unit,
        onFailure : (String) -> Unit
    )

    fun getMovieDetails(
        movieId: String,
//        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ): LiveData<MovieVO?>?

    fun getCreditsByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun searchMovie(query : String):Observable<List<MovieVO>>

    // Reactive streams only
    fun getNowPlayingMoviesObservable():Observable<List<MovieVO>>?
    fun getPopularMoviesObservable(): Observable<List<MovieVO>>?
    fun getTopRatedMoviesObservable(): Observable<List<MovieVO>>?
    fun getGenresObservable(): Observable<List<GenreVO>>?
    fun getActorObservable(): Observable<List<ActorVO>>?
    fun getMoviesByGenreObservable(genreId: String):Observable<List<MovieVO>>?
    fun getMovieByIdObservable(movieId: String): Observable<MovieVO>?
    fun getCreditsByMovieObservable(movieId: String): Observable<Pair<List<ActorVO>, List<ActorVO>>>?
}