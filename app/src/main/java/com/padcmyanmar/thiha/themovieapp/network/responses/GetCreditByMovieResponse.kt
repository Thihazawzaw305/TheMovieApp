package com.padcmyanmar.thiha.themovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.thiha.themovieapp.data.vos.Vos.ActorVO

class GetCreditByMovieResponse(

    @SerializedName("cast")
    val cast: List<ActorVO>?,

    @SerializedName("crew")
    val crew: List<ActorVO>?
)