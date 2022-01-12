package com.example.materialdesignhomework.viewmodel

import com.example.materialdesignhomework.model.imageofmars.ListOfPhotos


sealed class AppStateLatestImageMars{

    data class Success(val serverResponseData: ListOfPhotos) : AppStateLatestImageMars()

    data class Error(val error: Throwable) : AppStateLatestImageMars()

    data class Loading(val progress: Int?) : AppStateLatestImageMars()
}

