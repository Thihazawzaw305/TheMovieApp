package com.padcmyanmar.thiha.themovieapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.thiha.themovieapp.data.vos.Vos.MovieVO
import com.padcmyanmar.thiha.themovieapp.delegates.BannerViewHolderDelegate
import com.padcmyanmar.thiha.themovieapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_item_banner.view.*


class BannerViewHolder(itemView: View, private val mDelegate : BannerViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {

  private var mMovie : MovieVO? = null

    init {
        itemView.setOnClickListener{
            mMovie?.let {
                mDelegate.onTapMovieFromBanner(it.id)
            }

        }
    }
    fun bindData(movie : MovieVO){

        mMovie = movie

        Glide.with(itemView.context)
            .load("${IMAGE_BASE_URL}${movie.posterPath}")
            .into(itemView.ivBannerImage)

        itemView.tvBannerMovieName.text = movie.title

    }


}

