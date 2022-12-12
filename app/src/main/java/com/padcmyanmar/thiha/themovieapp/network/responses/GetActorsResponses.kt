package com.padcmyanmar.thiha.themovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.thiha.themovieapp.data.vos.Vos.ActorVO

data class GetActorsResponses (
    @SerializedName( "results")
    val results: List<ActorVO>?
        )