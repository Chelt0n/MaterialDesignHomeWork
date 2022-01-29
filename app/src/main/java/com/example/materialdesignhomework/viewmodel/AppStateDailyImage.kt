package com.example.materialdesignhomework.viewmodel

import com.example.materialdesignhomework.model.imageofday.NASAImageResponse


sealed class AppStateDailyImage {

    data class Success(val serverResponseData: NASAImageResponse) : AppStateDailyImage()

    data class Error(val error: Throwable) : AppStateDailyImage()

    data class Loading(val progress: Int?) : AppStateDailyImage()
}

