package com.padcmyanmar.thiha.themovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.thiha.themovieapp.data.vos.Vos.DateVO
import com.padcmyanmar.thiha.themovieapp.data.vos.Vos.MovieVO

data class MovieListResponse (
    @SerializedName("page")
    val page: Int?,
    @SerializedName("dates")
    val dates: DateVO?,
    @SerializedName("results")
    val results : List<MovieVO>?



        )