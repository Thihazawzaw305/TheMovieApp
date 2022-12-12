package com.padcmyanmar.thiha.themovieapp.mvp.prestenters

import androidx.lifecycle.LifecycleOwner

interface IBasePresenter {
    fun onUiReady(owner : LifecycleOwner)
}