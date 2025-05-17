package com.example.countriesassessment.domain.usecases

import com.example.countriesassessment.domain.data.CountryDomain
import com.example.countriesassessment.data.repository.CountryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(private val repository: CountryRepository) {

    suspend operator fun invoke(): Flow<Result<List<CountryDomain>>> = flow {
        try{
            val countries = repository.getCountries()
            emit(countries)
        }catch (e:Exception){
            emit(Result.failure(e))
        }
    }
}