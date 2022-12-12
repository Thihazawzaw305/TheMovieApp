package com.padcmyanmar.thiha.themovieapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.padcmyanmar.thiha.themovieapp.R
import com.padcmyanmar.thiha.themovieapp.adapters.BannerAdapter
import com.padcmyanmar.thiha.themovieapp.adapters.ShowcaseAdapter
import com.padcmyanmar.thiha.themovieapp.data.vos.Vos.GenreVO
import com.padcmyanmar.thiha.themovieapp.delegates.BannerViewHolderDelegate
import com.padcmyanmar.thiha.themovieapp.delegates.MovieViewHolderDelegate
import com.padcmyanmar.thiha.themovieapp.delegates.ShowcaseViewHolderDelegate
import com.padcmyanmar.thiha.themovieapp.mvi.intents.MainIntent
import com.padcmyanmar.thiha.themovieapp.mvi.mvibase.MVIView
import com.padcmyanmar.thiha.themovieapp.mvi.states.MainState
import com.padcmyanmar.thiha.themovieapp.mvp.prestenters.MainPresenter
import com.padcmyanmar.thiha.themovieapp.mvvm.MainViewModel
import com.padcmyanmar.thiha.themovieapp.routers.navigateToMovieSearchActivity
import com.padcmyanmar.thiha.themovieapp.viewpods.ActorListViewPod
import com.padcmyanmar.thiha.themovieapp.viewpods.MovieListViewPod
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_movie_details.*


class MainActivity : AppCompatActivity() ,
//    MainView
    BannerViewHolderDelegate, ShowcaseViewHolderDelegate,MovieViewHolderDelegate,
        MVIView<MainState>

{

    // view pod
    lateinit var mBannerAdapter: BannerAdapter
    lateinit var mShowcaseAdapter: ShowcaseAdapter
    lateinit var mBestPopularMovieListViewPod: MovieListViewPod
    lateinit var mMoviesByGenreViewPod: MovieListViewPod
    lateinit var mActorListViewPod: ActorListViewPod
// View Model
    private lateinit var mViewModel:com.padcmyanmar.thiha.themovieapp.mvi.viewmodels.MainViewModel
    // Presenter
    private lateinit var mPresenter: MainPresenter

//model
//    private val mMovieModel : MovieModel = MovieModelImpl
// data
//    private var mGenres : List<GenreVO>?  = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpViewModel()
//        setUpPresenter()
        setUpToolbar()
        setUpViewPods()
    //    onTapSearch()
        setUpBannerViewPager()
        // setUpGenreTabLayout()
        setUplisteners()
        setUpShowCaseRecyclerView()
//       mPresenter.onUiReady(this)
        //observe Live Data
 //       observeLiveData()
        setIntialIntents()
        observeState()



    }
    private fun observeState(){
        mViewModel.state.observe(this,this::render)
    }

    private  fun setUpViewModel(){
        mViewModel = ViewModelProvider(this)[com.padcmyanmar.thiha.themovieapp.mvi.viewmodels.MainViewModel::class.java]
//        mViewModel.getInitialData()
    }

    private fun setIntialIntents(){
        mViewModel.processIntent(MainIntent.LoadAllHomePageData,this)
    }

//    private fun  observeLiveData(){
//        mViewModel.nowPlayingMovieLiveData?.observe(this,mBannerAdapter::setNewData)
//        mViewModel.popularMovieLiveData?.observe(this,mBestPopularMovieListViewPod::setData)
//        mViewModel.topRatedMovieLiveData?.observe(this,mShowcaseAdapter::setNewData)
//        mViewModel.genresLiveData.observe(this,this::setUpGenreTabLayout)
//        mViewModel.moviesByGenreLiveData.observe(this,mMoviesByGenreViewPod::setData)
//        mViewModel.actorsLiveData.observe(this,mActorListViewPod::setData)
//    }

//    private fun setUpPresenter() {
//        mPresenter = ViewModelProvider(this)[MainPresenterImpl::class.java]
//        mPresenter.initView(this)
//    }


//private fun requestData(){
////Now playing Movies
////    mMovieModel.getNowPlayingMovies(
////        onSuccess = {
////            mBannerAdapter.setNewData(it)
////        },
////        onFailure = {
////            /// Show error Message
////        }
////    )
//    mMovieModel.getNowPlayingMovies {
//        showError(it)
//    }?.observe(this, Observer {
//        mBannerAdapter.setNewData(it)
//    })
////get popular movies
////    mMovieModel.getPopularMovies(
////        onSuccess = {
////            mBestPopularMovieListViewPod.setData(it)
////        },
////        onFailure = {
////            /// Show error Message
////        }
////    )
//    mMovieModel.getPopularMovies {
//        showError(it)
//    }?.observe(this, Observer {
//        mBestPopularMovieListViewPod.setData(it)
//    })
////get top rated
////    mMovieModel.getTopRatedMovies(
////        onSuccess = {
////            mShowcaseAdapter.setNewData(it)
////        },
////        onFailure = {
////            /// Show error Message
////        }
////    )
//    mMovieModel.getTopRatedMovies {
//        showError(it)
//    }?.observe(this, Observer {
//        mShowcaseAdapter.setNewData(it)
//    })
//// get genre
//    mMovieModel.getGenres(
//        onSuccess = {
//            mGenres = it
//            setUpGenreTabLayout(it)
//
//            /// get Movies by genre for fist genre
//            it.firstOrNull()?.id?.let{genreId ->
//                getMoviesByGenre(genreId)
//            }
//        },
//        onFailure = {
//            /// show Error Message
//        }
//    )
//// get Actors
//    mMovieModel.getActors(
//        onSuccess = {
//            mActorListViewPod.setData(it)
//        },
//        onFailure = {
//            //show error message
//        }
//    )
////
//
//}

//    private fun getMoviesByGenre(genreId: Int) {
//        mMovieModel.getMoviesByGenre(genreId = genreId.toString(),
//            onSuccess = {
//                mMoviesByGenreViewPod.setData(it)
//
//            }, onFailure = {
//
//                //show error message
//            }
//        )
//
//
//    }

    // ViewPods setup

    private fun setUpViewPods() {
        mActorListViewPod = vpBestActor as ActorListViewPod
        mBestPopularMovieListViewPod = vpBestPopularMovieList as MovieListViewPod
        mBestPopularMovieListViewPod.setUpMovieListViewPod(mPresenter)
        mMoviesByGenreViewPod = vpMoviesByGenre as MovieListViewPod
        mMoviesByGenreViewPod.setUpMovieListViewPod(mPresenter)

    }

    // ViewPager
    private fun setUpBannerViewPager() {
        mBannerAdapter = BannerAdapter(mPresenter)
        viewPagerBanner.adapter = mBannerAdapter
        // attach dotsIndicator
        dotsIndicatorBanner.attachTo(viewPagerBanner)
    }

    // App Bar leading Icon
    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
    }

    // tab
    private fun setUpGenreTabLayout(genreList: List<GenreVO>) {
        genreList.forEach {
            tabLayoutGenre.newTab().apply {
                text = it.name
                tabLayoutGenre.addTab(this)
            }

//          val tab = tabLayoutGenre.newTab()
//           tab.text = it
//           tabLayoutGenre.addTab(tab)
        }
    }
//
    private fun setUplisteners() {
        // Genre Tab Layout
        tabLayoutGenre.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
 //             mPresenter.onTapGenre(tab?.position?:0)
 //               mViewModel.getMovieByGenre(tab?.position?:0)
                mViewModel.processIntent(
                    MainIntent.LoadMoviesByGenreIntent(tab?.position?:0),
                    this@MainActivity
                )


                }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }


        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.btnSearch){
            navigateToMovieSearchActivity()
            return true
        }
        return false
    }

    // showcase
    private fun setUpShowCaseRecyclerView() {
        mShowcaseAdapter = ShowcaseAdapter(mPresenter)
        rvShowCases.adapter = mShowcaseAdapter
        rvShowCases.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_discover, menu)
        return true
    }

    override fun onTapMovieFromBanner(movieId: Int) {
        startActivity(MovieDetailsActivity.newIntent(this, movieId = movieId))
    }

    override fun onTapMovieFromShowcase(movieId: Int) {
        startActivity(MovieDetailsActivity.newIntent(this, movieId = movieId))
    }

    override fun onTapMovie(movieId: Int) {
        startActivity(MovieDetailsActivity.newIntent(this, movieId = movieId))
    }

    override fun render(state: MainState) {
      if (state.errorMessage.isEmpty()){
          showError(state.errorMessage)
      }
        Log.d("newStateInView","${state.nowPlayingMovies.count()}")
        mBannerAdapter.setNewData(state.nowPlayingMovies)
        mBestPopularMovieListViewPod.setData((state.popularMovies))
        mShowcaseAdapter.setNewData(state.topRatedMovies)
        setUpGenreTabLayout(state.genres)
        mMoviesByGenreViewPod.setData(state.moviesByGenre)
        mActorListViewPod.setData(state.actors)
    }

//    override fun showNowPlayingMovies(nowPlayingMovies: List<MovieVO>) {
//      mBannerAdapter.setNewData(nowPlayingMovies)
//    }
//
//    override fun showPopularMovies(popularMovies: List<MovieVO>) {
//      mBestPopularMovieListViewPod.setData(popularMovies)
//    }
//
//    override fun showTopRatedMovies(topRatedMovies: List<MovieVO>) {
//     mShowcaseAdapter.setNewData(topRatedMovies)
//    }
//
//    override fun showGenres(genreList: List<GenreVO>) {
//      setUpGenreTabLayout(genreList)
//    }
//
//    override fun showMoviesByGenre(moviesByGenre: List<MovieVO>) {
//      mMoviesByGenreViewPod.setData(moviesByGenre)
//    }
//
//    override fun showActor(actors: List<ActorVO>) {
//    mActorListViewPod.setData(actors)
//    }
//
//    override fun navigateToMovieDetailsScreen(movieId: Int) {
//        navigateToMovieDetailsActivity(movieId)
//    }
//
//    override fun showError(errorString: String) {
//
//    }

    private fun showError(error: String) {
        Snackbar.make(window.decorView, error, Snackbar.LENGTH_LONG).show()
    }

//    private fun onTapSearch() {
//        btnSearch.setOnClickListener {
//            startActivity(MovieSearchActivity.newIntent(this))
//        }
//
//    }

}
