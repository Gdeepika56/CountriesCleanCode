package com.example.countriesassessment.data.mapper

import com.example.countriesassessment.domain.data.CountryDomain
import com.example.countriesassessment.data.model.CountriesItem

fun CountriesItem.toDomain(): CountryDomain {
    return CountryDomain(
        name = this.name,
        capital = this.capital,
        code = this.code,
        region = this.region
    )
}