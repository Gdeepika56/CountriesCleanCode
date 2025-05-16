package com.example.countriesassessment.usecases

import com.example.countriesassessment.model.CountriesItem
import com.example.countriesassessment.repository.CountryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(private val repository: CountryRepository) {

    suspend operator fun invoke(): Flow<Result<List<CountriesItem>>> = flow {
        try{
            val countries = repository.getCountries()
            emit(countries)
        }catch (e:Exception){
            emit(Result.failure(e))
        }
    }
}