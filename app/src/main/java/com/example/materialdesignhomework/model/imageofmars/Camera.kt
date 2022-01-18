package com.example.materialdesignhomework.model.imageofmars

import com.google.gson.annotations.SerializedName

data class Camera(
    val id: Long,
    val name: String,

    @SerializedName("rover_id")
    val roverID: Long,

    @SerializedName("full_name")
    val fullName: String
)
