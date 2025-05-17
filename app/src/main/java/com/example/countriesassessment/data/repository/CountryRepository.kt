package com.example.countriesassessment.data.repository

import com.example.countriesassessment.domain.data.CountryDomain

interface CountryRepository  {
    suspend fun getCountries(): Result<List<CountryDomain>>
}