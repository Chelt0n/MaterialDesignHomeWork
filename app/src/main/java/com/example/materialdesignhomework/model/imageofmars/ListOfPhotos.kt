package com.example.materialdesignhomework.model.imageofmars

import com.google.gson.annotations.SerializedName

data class ListOfPhotos(
    @SerializedName("latest_photos")
    val photos: List<Photo>
)
