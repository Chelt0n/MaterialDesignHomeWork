package com.example.materialdesignhomework.model.imageofmars

import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("id")
    val id: Long,
    @SerializedName("sol")
    val sol: Long,
    @SerializedName("camera")
    val camera: Camera,
    @SerializedName("img_src")
    val img_src: String,
    @SerializedName("earth_date")
    val earthDate: String,
    @SerializedName("rover")
    val rover: Rover
)
