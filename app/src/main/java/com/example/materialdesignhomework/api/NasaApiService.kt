package com.example.materialdesignhomework.api

import com.example.materialdesignhomework.model.imageofday.NASAImageResponse
import com.example.materialdesignhomework.model.imageofmars.ListOfPhotos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApiService {

    @GET("planetary/apod")
    fun getImage(@Query("api_key") apiKey: String): Call<NASAImageResponse>

    @GET("latest_photos/")
    fun getNasaImages(@Query("api_key") apiKey: String):Call<ListOfPhotos>
}