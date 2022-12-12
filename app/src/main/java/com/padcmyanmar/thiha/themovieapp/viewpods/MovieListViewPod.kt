package com.padcmyanmar.thiha.themovieapp.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.thiha.themovieapp.adapters.MovieAdapter
import com.padcmyanmar.thiha.themovieapp.data.vos.Vos.MovieVO
import com.padcmyanmar.thiha.themovieapp.delegates.MovieViewHolderDelegate
import kotlinx.android.synthetic.main.view_pod_movie_list.view.*

class MovieListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {
    lateinit var mMovieAdapter: MovieAdapter
    lateinit var mDelegate: MovieViewHolderDelegate



    override fun onFinishInflate() {
       // setUpMovieRecyclerView()
        super.onFinishInflate()
    }

    fun setUpMovieListViewPod(delegate: MovieViewHolderDelegate){
        setDelegate(delegate)
        setUpMovieRecyclerView()
    }

    fun setData(movieList: List<MovieVO>){
        mMovieAdapter.setNewData(movieList)
    }

    private fun setDelegate(delegate: MovieViewHolderDelegate){
        this.mDelegate = delegate
    }

   private fun setUpMovieRecyclerView(){
       mMovieAdapter = MovieAdapter(mDelegate)
       rvMovielist.adapter = mMovieAdapter
       rvMovielist.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

   }

}