package com.example.countriesassessment.domain.repository

import com.example.countriesassessment.domain.model.CountriesItem

interface CountryRepository  {
    suspend fun getCountries(): Result<List<CountriesItem>>
}