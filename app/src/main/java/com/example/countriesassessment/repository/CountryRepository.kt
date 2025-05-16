package com.example.countriesassessment.repository

import com.example.countriesassessment.model.CountriesItem

interface CountryRepository  {
    suspend fun getCountries(): Result<List<CountriesItem>>
}