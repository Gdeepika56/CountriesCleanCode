package com.example.countriesassessment.data.remote

import com.example.countriesassessment.commons.Constants.Countries_Endpoint
import com.example.countriesassessment.data.model.CountriesItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(Countries_Endpoint)
    suspend fun getCountries():Response<List<CountriesItem>>
}