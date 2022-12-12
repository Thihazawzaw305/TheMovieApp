package com.padcmyanmar.thiha.themovieapp.data.vos.Vos

import com.google.gson.annotations.SerializedName

data class CollectionVO(


    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("name")
    val name : String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,

)