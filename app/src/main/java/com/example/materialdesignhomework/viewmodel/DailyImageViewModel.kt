package com.example.materialdesignhomework.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.materialdesignhomework.BuildConfig
import com.example.materialdesignhomework.model.imageofday.NASAImageResponse
import com.example.materialdesignhomework.api.NasaApiRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DailyImageViewModel(
    private val liveDataForViewToObserve: MutableLiveData<AppStateDailyImage> = MutableLiveData(),
    private val retrofitImpl: NasaApiRetrofit = NasaApiRetrofit(),
) : ViewModel() {

    fun getImageData(): LiveData<AppStateDailyImage> {
        sendServerRequest()
        return liveDataForViewToObserve
    }

    private fun sendServerRequest() {
        liveDataForViewToObserve.value = AppStateDailyImage.Loading(null)

        val apiKey = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            AppStateDailyImage.Error(Throwable("You need API key"))
        } else {
            executeImageRequest(apiKey)
        }
    }

    private fun executeImageRequest(apiKey: String) {

        val url = "https://api.nasa.gov/"
        val callback = object : Callback<NASAImageResponse> {

            override fun onResponse(
                call: Call<NASAImageResponse>,
                response: Response<NASAImageResponse>
            ) {
                handleImageResponse(response)
            }

            override fun onFailure(call: Call<NASAImageResponse>, t: Throwable) {
                liveDataForViewToObserve.value = AppStateDailyImage.Error(t)
            }
        }

        retrofitImpl.getNasaService(url).getImage(apiKey).enqueue(callback)
    }

    private fun handleImageResponse(response: Response<NASAImageResponse>) {
        if (response.isSuccessful && response.body() != null) {
            liveDataForViewToObserve.value = AppStateDailyImage.Success(response.body()!!)
            return
        }

        val message = response.message()
        if (message.isNullOrEmpty()) {
            liveDataForViewToObserve.value = AppStateDailyImage.Error(Throwable("Unidentified error"))
        } else {
            liveDataForViewToObserve.value = AppStateDailyImage.Error(Throwable(message))
        }
    }
}
