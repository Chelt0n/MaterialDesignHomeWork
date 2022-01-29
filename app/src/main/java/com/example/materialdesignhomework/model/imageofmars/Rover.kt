package com.example.materialdesignhomework.model.imageofmars

import com.google.gson.annotations.SerializedName

data class Rover(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("landing_date")
    val landingDate: String,
    @SerializedName("launch_date")
    val launchDate: String,
    @SerializedName("status")
    val status: String
)
