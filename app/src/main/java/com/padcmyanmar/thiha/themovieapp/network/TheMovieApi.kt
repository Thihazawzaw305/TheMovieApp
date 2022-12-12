package com.padcmyanmar.thiha.themovieapp.network

import com.padcmyanmar.thiha.themovieapp.data.vos.Vos.MovieVO
import com.padcmyanmar.thiha.themovieapp.network.responses.GetActorsResponses
import com.padcmyanmar.thiha.themovieapp.network.responses.GetCreditByMovieResponse
import com.padcmyanmar.thiha.themovieapp.network.responses.GetGenresResponses
import com.padcmyanmar.thiha.themovieapp.network.responses.MovieListResponse
import com.padcmyanmar.thiha.themovieapp.utils.*
import io.reactivex.rxjava3.core.Observable

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface TheMovieApi {
    @GET(API_GET_NOW_PLAYING)
    fun getNowPlayingMovies(
        @Query(PARAM_API_KEY) apiKey:String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1,

    ): Observable<MovieListResponse>
//    io.reactivex.rxjava3.core.Observable<MovieListResponse>
//            Call<MovieListResponse>


    @GET(API_GET_POPULAR_MOVIES)
    fun getPopularMovies(
        @Query(PARAM_API_KEY) apiKey:String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1,
    ): Observable<MovieListResponse>

    @GET(API_GET_TOP_RATED_MOVIES)
    fun getTopRatedMovies(
        @Query(PARAM_API_KEY) apiKey:String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1,

    ):Observable<MovieListResponse>

    @GET(API_GET_GENRES)
    fun getGenres(
        @Query(PARAM_API_KEY) apiKey:String = MOVIE_API_KEY,
    ): Observable<GetGenresResponses>

    @GET(API_GET_MOVIES_BY_GENRE)
    fun getMovieByGenre(
        @Query(PARAM_GENRE_ID) genreId: String,
        @Query(PARAM_API_KEY) apiKey:String = MOVIE_API_KEY,
    ): Observable<MovieListResponse>

    @GET(API_GET_ACTORS)
    fun getActors(
        @Query(PARAM_API_KEY) apiKey:String = MOVIE_API_KEY,
    ): Observable<GetActorsResponses>

    @GET("$API_GET_MOVIE_DETAILS/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id")movieId : String,
        @Query(PARAM_API_KEY) apiKey:String = MOVIE_API_KEY,
    ):Observable<MovieVO>

    @GET("$API_GET_CREDITS_BY_MOVIE/{movie_id}/credits")
    fun getCreditsByMovie(
        @Path("movie_id") movieId: String,
        @Query(PARAM_API_KEY) apiKey:String = MOVIE_API_KEY,
        ): Observable<GetCreditByMovieResponse>

    @GET(API_SEARCH_MOVIE)
    fun searchMovie(
        @Query(PARAM_API_KEY) apiKey:String = MOVIE_API_KEY,
       @Query(PARAM_QUERY) query : String
    ): Observable<MovieListResponse>
}