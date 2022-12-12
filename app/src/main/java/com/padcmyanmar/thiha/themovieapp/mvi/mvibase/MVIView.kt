package com.padcmyanmar.thiha.themovieapp.mvi.mvibase

interface MVIView<S: MVIState> {
    fun render(state: S)
}