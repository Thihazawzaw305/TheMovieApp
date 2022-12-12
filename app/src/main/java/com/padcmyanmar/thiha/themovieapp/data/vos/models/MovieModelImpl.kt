package com.padcmyanmar.thiha.themovieapp.data.vos.models

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import  io.reactivex.rxjava3.core.Observable
import com.padcmyanmar.thiha.themovieapp.data.vos.Vos.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers


object MovieModelImpl : BaseModel(),MovieModel {
    //Data agent
//    private val mMovieDataAgent: MovieDataAgent = RetrofitDataAgentImpl

    // Data base
//    private var mMovieDatabase: MovieDatabase? = null
//
//    fun initDatabase(context : Context){
//        mMovieDatabase = MovieDatabase.getDBInstance(context )
//    }
    @SuppressLint("CheckResult")
    override fun getNowPlayingMovies(
    //    onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ): LiveData<List<MovieVO>>?

    {
        // Data base
//        onSuccess(mMovieDatabase?.movieDao()?.getMovieByType(type = NOW_PLAYING)?: listOf())
        //Network
//        mMovieDataAgent.getNowPlayingMovies(onSuccess = {
//           it.forEach{movie -> movie.type = NOW_PLAYING}
//           mMovieDatabase?.movieDao()?.insertMovies(it)
//           onSuccess(it)
//        }, onFailure = onFailure)
        mMovieApi.getNowPlayingMovies(page = 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.forEach{movie -> movie.type = NOW_PLAYING}
                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())
             //   onSuccess(it.results ?: listOf())

            }, {
                onFailure(it.localizedMessage?:"")
        })
        return mMovieDatabase?.movieDao()?.getMovieByType(type = NOW_PLAYING)
    }





    @SuppressLint("CheckResult")
    override fun getPopularMovies(
 //       onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit): LiveData<List<MovieVO>>?

    {

        // Data base
 //       onSuccess(mMovieDatabase?.movieDao()?.getMovieByType(type = POPULAR)?: listOf())
        //Network
//        mMovieDataAgent.getPopularMovies(onSuccess = {
//            it.forEach{movie -> movie.type = POPULAR}
//            mMovieDatabase?.movieDao()?.insertMovies(it)
//            onSuccess(it)
//        }, onFailure = onFailure)
        mMovieApi.getPopularMovies(page = 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.forEach{movie -> movie.type = POPULAR}
                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())
//                onSuccess(it.results ?: listOf())

            }, {
                onFailure(it.localizedMessage?:"")
            })
        return mMovieDatabase?.movieDao()?.getMovieByType(type = POPULAR)
    }
    @SuppressLint("CheckResult")
    override fun getTopRatedMovies(
 //       onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ): LiveData<List<MovieVO>>?

    {


        // Data base
//        onSuccess(mMovieDatabase?.movieDao()?.getMovieByType(type = TOP_RATED)?: listOf())
        //Network
//        mMovieDataAgent.getTopRatedMovies(onSuccess =
//        {
//            it.forEach{movie -> movie.type = TOP_RATED}
//            mMovieDatabase?.movieDao()?.insertMovies(it)
//            onSuccess(it)
//        }, onFailure = onFailure)
        mMovieApi.getTopRatedMovies(page = 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.forEach{movie -> movie.type = TOP_RATED}
                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())
 //               onSuccess(it.results ?: listOf())

            }, {
                onFailure(it.localizedMessage?:"")
            })
        return mMovieDatabase?.movieDao()?.getMovieByType(type = TOP_RATED)
    }
    @SuppressLint("CheckResult")
    override fun getGenres(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {
    //    mMovieDataAgent.getGenres(onSuccess = onSuccess, onFailure = onFailure)
        mMovieApi.getGenres()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it.genres  ?: listOf())
            },{
                onFailure(it.localizedMessage?:"")
            })

    }
    @SuppressLint("CheckResult")
    override fun getMoviesByGenre(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
//        mMovieDataAgent.getMoviesByGenres(
//            genreId = genreId,
//            onSuccess = onSuccess,
//            onFailure = onFailure
//        )
        mMovieApi.getMovieByGenre(genreId = genreId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it.results?: listOf())
            },{
                onFailure(it.localizedMessage?:"")
            })
    }
    @SuppressLint("CheckResult")
    override fun getActors(onSuccess: (List<ActorVO>) -> Unit, onFailure: (String) -> Unit) {
//        mMovieDataAgent.getActors(
//            onSuccess = onSuccess,
//            onFailure = onFailure
//        )
        mMovieApi.getActors()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it.results?: listOf())
            },{
                onFailure(it.localizedMessage?:"")
            }
            )
    }
    @SuppressLint("CheckResult")
    override fun getMovieDetails(
        movieId: String,
 //       onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) :LiveData<MovieVO?>? {

        //Data base

 //       val movieFromDatabase = mMovieDatabase?.movieDao()?.getMovieById(movieId = movieId.toInt())
 //       movieFromDatabase?.let {
  //          onSuccess(it)
 //       }
        //network
//        mMovieDataAgent.getMovieDetails(
//            movieId = movieId,
//            onSuccess = {
//                val movieFromDatabase = mMovieDatabase?.movieDao()?.getMovieById(movieId = movieId.toInt())
//                it.type = movieFromDatabase?.type
//                mMovieDatabase?.movieDao()?.insertSingleMovie(it)
//                onSuccess(it)
//            },
//            onFailure = onFailure
//        )
        mMovieApi.getMovieDetails(movieId = movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val movieFromDatabaseToSync =
                    mMovieDatabase?.movieDao()?.getMovieByIdOneTime(movieId = movieId.toInt())
                it.type = movieFromDatabaseToSync?.type
                mMovieDatabase?.movieDao()?.insertSingleMovie(it)
//                onSuccess(it)
            },{
                onFailure(it.localizedMessage?:"")
            })
        return mMovieDatabase?.movieDao()?.getMovieById(movieId = movieId.toInt())
    }
    @SuppressLint("CheckResult")
    override fun getCreditsByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
//        mMovieDataAgent.getCreditByMovie(
//            movieId = movieId,
//            onSuccess = onSuccess,
//            onFailure = onFailure
//        )
        mMovieApi.getCreditsByMovie(movieId = movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(Pair(it.cast?: listOf(), it.crew?: listOf()))
            },
                {
                    onFailure(it.localizedMessage?:"")
                })
    }
    @SuppressLint("CheckResult")
    override fun searchMovie(query: String):Observable<List<MovieVO>>{
        return mMovieApi
            .searchMovie(query = query)
            .map{it.results?: listOf()}
            .onErrorResumeNext{Observable.just(listOf())}
            .subscribeOn(Schedulers.io())


    }

    override fun getNowPlayingMoviesObservable(): Observable<List<MovieVO>>? {
        mMovieApi.getNowPlayingMovies(page = 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                it.results?.forEach{movie -> movie.type = NOW_PLAYING}
                mMovieDatabase?.movieDao()?.insertMovies(it.results?: listOf())
            }
        return mMovieDatabase?.movieDao()
            ?.getMoviesByTypeFlowable(type = NOW_PLAYING)
            ?.toObservable()
    }

    override fun getPopularMoviesObservable(): Observable<List<MovieVO>>? {
        mMovieApi.getPopularMovies(page = 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                it.results?.forEach{movie -> movie.type = POPULAR}
                mMovieDatabase?.movieDao()?.insertMovies(it.results?: listOf())
            }
        return mMovieDatabase?.movieDao()
            ?.getMoviesByTypeFlowable(type = POPULAR)
            ?.toObservable()    }

    override fun getTopRatedMoviesObservable(): Observable<List<MovieVO>>? {
        mMovieApi.getTopRatedMovies(page = 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                it.results?.forEach{movie -> movie.type = TOP_RATED}
                mMovieDatabase?.movieDao()?.insertMovies(it.results?: listOf())
            }
        return mMovieDatabase?.movieDao()
            ?.getMoviesByTypeFlowable(type = TOP_RATED)
            ?.toObservable()    }

    override fun getGenresObservable(): Observable<List<GenreVO>>? {
     return mMovieApi.getGenres()
         .map { it.genres?: listOf() }
         .subscribeOn(Schedulers.io())
    }

    override fun getActorObservable(): Observable<List<ActorVO>>? {
        return mMovieApi.getActors()
            .map { it.results?: listOf() }
            .subscribeOn(Schedulers.io())
    }

    override fun getMoviesByGenreObservable(genreId: String): Observable<List<MovieVO>>? {
        return mMovieApi.getMovieByGenre(genreId = genreId)
            .map { it.results?: listOf() }
            .subscribeOn(Schedulers.io())
    }

    override fun getMovieByIdObservable(movieId: String): Observable<MovieVO>? {
        mMovieApi.getMovieDetails(movieId = movieId.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                val movieFromDatabaseToSync =
                    mMovieDatabase?.movieDao()?.getMovieByIdOneTime(movieId = movieId.toInt())
                it.type = movieFromDatabaseToSync?.type
                mMovieDatabase?.movieDao()?.insertSingleMovie(it)
            }
       return mMovieDatabase
           ?.movieDao()
           ?.getMovieIdFlowable(movieId = movieId.toInt())
           ?.toObservable()
            }

    override fun getCreditsByMovieObservable(movieId: String): Observable<Pair<List<ActorVO>, List<ActorVO>>>? {
       return mMovieApi.getCreditsByMovie(movieId = movieId.toString())
           .map { Pair(it.cast?: listOf(), it.crew?: listOf()) }
           .subscribeOn(Schedulers.io())
    }
}



