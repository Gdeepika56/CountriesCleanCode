package com.example.countriesassessment.data.model

import com.google.gson.annotations.SerializedName

data class CountriesItem(
    @SerializedName("capital")
    val capital: String,

    @SerializedName("code")
    val code: String,

    @SerializedName("currency")
    val currency: Currency,

    @SerializedName("language")
    val language: Language,

    @SerializedName("name")
    val name: String,

    @SerializedName("region")
    val region: String,

    val demonym: String,
    val flag: String,
)