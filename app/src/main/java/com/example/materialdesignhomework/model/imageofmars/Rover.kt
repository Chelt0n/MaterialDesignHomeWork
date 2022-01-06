package com.example.materialdesignhomework.model.imageofmars

import com.google.gson.annotations.SerializedName

data class Rover(
    val id: Long,
    val name: String,

    @SerializedName("landing_date")
    val landingDate: String,

    @SerializedName("launch_date")
    val launchDate: String,

    val status: String
)
