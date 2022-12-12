package com.padcmyanmar.thiha.themovieapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.padcmyanmar.thiha.themovieapp.R
import com.padcmyanmar.thiha.themovieapp.data.vos.Vos.ActorVO
import com.padcmyanmar.thiha.themovieapp.data.vos.Vos.GenreVO
import com.padcmyanmar.thiha.themovieapp.data.vos.Vos.MovieVO
import com.padcmyanmar.thiha.themovieapp.data.vos.models.MovieModel
import com.padcmyanmar.thiha.themovieapp.data.vos.models.MovieModelImpl
import com.padcmyanmar.thiha.themovieapp.mvp.prestenters.MovieDetailsPresenter
import com.padcmyanmar.thiha.themovieapp.mvp.prestenters.MovieDetailsPresenterImpl
import com.padcmyanmar.thiha.themovieapp.mvp.views.MovieDetailsView
import com.padcmyanmar.thiha.themovieapp.mvvm.MovieDetailsViewModel
import com.padcmyanmar.thiha.themovieapp.utils.IMAGE_BASE_URL
import com.padcmyanmar.thiha.themovieapp.viewpods.ActorListViewPod
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.view_holder_movie.*

class MovieDetailsActivity : AppCompatActivity(),
 MovieDetailsView

{




    companion object{
        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        fun newIntent(context: Context,movieId : Int) : Intent{
           val intent = Intent(context,MovieDetailsActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID,movieId)
            return intent

        }


    }
    // View Models
    private lateinit var mViewModel : MovieDetailsViewModel

    // presenter
    private lateinit var mPresenter : MovieDetailsPresenter

    // View Pods

    lateinit var actorsViewPod : ActorListViewPod
    lateinit var creatorsViewPod : ActorListViewPod
////models
//
//    private val mMovieModel : MovieModel = MovieModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        setUpPresenter()
        setUpViewPod()
        setUpListener()
        onTapSearch()

        val movieId = intent?.getIntExtra(EXTRA_MOVIE_ID,0)
        movieId?.let {
          //  Snackbar.make(window.decorView,movieId.toString(),Snackbar.LENGTH_LONG).show()
//            requestData(it)
            mPresenter.onUiReadyInMovieDetails(this,movieId)
 //           setUpViewModel(it)
        }
//        observeLiveData()

    }


    private fun setUpPresenter(){
        mPresenter = ViewModelProvider(this)[MovieDetailsPresenterImpl::class.java]
        mPresenter.initView(this)
    }

//    private fun  requestData(movieId: Int){
//        mMovieModel.getMovieDetails(
//            movieId = movieId.toString(),
////            onSuccess = {
////                bindData(it)
////
////            },
////            onFailure = {
////                //show error message
////            }
//        onFailure = {showError(it)}
//        )?.observe(this, Observer {
//            it?.let { movieDetails -> bindData(movieDetails)}
//        })
//
//        mMovieModel.getCreditsByMovie(
//            movieId = movieId.toString(),
//            onSuccess = {
//                actorsViewPod.setData(it.first)
//                creatorsViewPod.setData(it.second)
//
//            }, onFailure = {
//
//              showError(it)
//            }
//        )
//    }


    // setUp ViewModel

    private fun setUpViewModel(movieId : Int){
        mViewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)
        mViewModel.getInitialData(movieId)
    }


    // setUp observeLiveData

    private fun observeLiveData(){
        mViewModel.movieDetailsLiveData?.observe(this){
            it?.let { movie -> bindData(movie) }
        }
        mViewModel.castLiveData.observe(this,actorsViewPod::setData)
        mViewModel.crewLiveData.observe(this,creatorsViewPod::setData)
    }

    //setup view pod
    private fun setUpViewPod(){
        actorsViewPod = vpActor as ActorListViewPod
        actorsViewPod.setUpActorViewPod(
            backgroundColorReference = R.color.colorPrimary,
            titleText = getString(R.string.lbl_actors),
            moreTitleText = ""
        )
        creatorsViewPod = vpCreators as ActorListViewPod
        creatorsViewPod.setUpActorViewPod(
            backgroundColorReference = R.color.colorPrimary,
            titleText = getString(R.string.lbl_creators),
            moreTitleText = getString(R.string.lbl_more_creators)

        )
    }

    private fun setUpListener(){
        btnBack.setOnClickListener{
 //         mPresenter.onTapBack()
            super.onBackPressed()
        }
    }


    private fun bindData(movie: MovieVO){
        Glide.with(this)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(ivMovieDetails)
       tvMovieNameFromMovieDetails.text = movie.title ?:""
        tvMovieReleaseYear.text = movie.releaseDate?.substring(0,4)
        tvRating.text = movie.voteAverage?.toString()?:""
        movie.voteCount?.let {
            tvNumberOfVotes.text = "$it VOTES"

        }
        rbRatingMovieDetails.rating = movie.getRatingBaseOnFiveStars()

        bindGenres(movie,movie.genres ?: listOf())

        collapsingToolbar.title = movie.title?:""
        tvOverView.text = movie.overView ?:""
        tvOriginalTitle.text = movie.title ?:""
        tvTime.text = movie.calculateRunTime()
        tvType.text = movie.getGenresAsCommaSeparatedString()
        tvProduction.text = movie.getProductionCountriesAsCommaSeparatedString()
        tvPremiere.text = movie.releaseDate ?:""
        tvDescription.text = movie.overView ?:""

    }

    private fun bindGenres(
    movie: MovieVO,
    genres: List<GenreVO>
    ){


        movie.genres?.count()?.let{

            tvFirstGenre.text = genres.firstOrNull()?.name?:""
            tvSecondGenre.text = genres.getOrNull(1)?.name?:""
            tvThirdGenre.text = genres.getOrNull(2)?.name?:""

            if(it < 3){
                tvThirdGenre.visibility = View.GONE

            } else if(it < 2){
                tvSecondGenre.visibility = View.GONE
            }

        }
    }

    override fun showMovieDetails(movie: MovieVO) {
      bindData(movie)
    }

    override fun showCreditsByMovie(cast: List<ActorVO>, crew: List<ActorVO>) {
    actorsViewPod.setData(cast)
        creatorsViewPod.setData(crew)
    }
 
    override fun navigateBack() {
      finish()
    }

    override fun showError(error: String) {
        Snackbar.make(window.decorView, error, Snackbar.LENGTH_LONG).show()
    }


    private fun onTapSearch(){
        btnSearchFromMovieDetails.setOnClickListener{
            startActivity(MovieSearchActivity.newIntent(this))
        }
}}