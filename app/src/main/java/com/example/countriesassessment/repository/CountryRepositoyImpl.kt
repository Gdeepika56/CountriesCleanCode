package com.example.countriesassessment.repository

import com.example.countriesassessment.model.CountriesItem
import com.example.countriesassessment.remote.ApiService
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(private val apiService: ApiService): CountryRepository {

    override suspend fun getCountries(): Result<List<CountriesItem>> {
        return try{
            val response = apiService.getCountries()
           if(response.isSuccessful){
               val body =response.body()
               if(body != null){
                   Result.success(body)
               }else{
                   Result.failure(Exception("Response body is null"))
               }
           }else{
               Result.failure(Exception("Error: ${response.code()} ${response.message()}"))
           }

        }catch (e:Exception){
            Result.failure(e)
        }
    }


}