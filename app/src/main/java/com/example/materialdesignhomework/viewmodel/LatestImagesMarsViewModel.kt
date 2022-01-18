package com.example.materialdesignhomework.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.materialdesignhomework.BuildConfig
import com.example.materialdesignhomework.api.NasaApiRetrofit
import com.example.materialdesignhomework.model.imageofmars.ListOfPhotos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LatestImagesMarsViewModel(
    private val retrofitImp: NasaApiRetrofit = NasaApiRetrofit()
) : ViewModel() {

    private val liveDataToObserve: MutableLiveData<AppStateLatestImageMars> = MutableLiveData()


    fun getPhotos(): LiveData<AppStateLatestImageMars> {
        sendServerRequest()
        return liveDataToObserve
    }

    private fun sendServerRequest() {

        liveDataToObserve.value = AppStateLatestImageMars.Loading(null)

        val apiKey = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            AppStateDailyImage.Error(Throwable("You need API key"))
        } else {
            requestPhotos(apiKey)
        }
        requestPhotos(apiKey)
    }

    private fun requestPhotos(apiKey: String) {
        val url = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/"

        val callback = object : Callback<ListOfPhotos> {
            override fun onResponse(call: Call<ListOfPhotos>, response: Response<ListOfPhotos>) {
                liveDataToObserve.value = AppStateLatestImageMars.Success(response.body()!!)
            }

            override fun onFailure(call: Call<ListOfPhotos>, t: Throwable) {
                liveDataToObserve.value = AppStateLatestImageMars.Error(Throwable("error"))
            }
        }
        retrofitImp.getNasaService(url).getNasaImages(apiKey).enqueue(callback)
    }

}