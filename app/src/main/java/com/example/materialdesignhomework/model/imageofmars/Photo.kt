package com.example.materialdesignhomework.model.imageofmars

import com.google.gson.annotations.SerializedName

data class Photo(
    val id: Long,
    val sol: Long,
    val camera: Camera,

    @SerializedName("img_src")
    val img_src: String,

    @SerializedName("earth_date")
    val earthDate: String,

    val rover: Rover
)
