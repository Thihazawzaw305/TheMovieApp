package com.padcmyanmar.thiha.themovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.thiha.themovieapp.data.vos.Vos.GenreVO

data class GetGenresResponses (
    @SerializedName("genres")
    val genres: List<GenreVO>?
        )