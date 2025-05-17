package com.example.countriesassessment.data.repository

import com.example.countriesassessment.data.remote.ApiService
import com.example.countriesassessment.domain.data.CountryDomain
import com.example.countriesassessment.data.mapper.toDomain
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(private val apiService: ApiService):
    CountryRepository {

    override suspend fun getCountries(): Result<List<CountryDomain>> {
        return try{
            val response = apiService.getCountries()
           if(response.isSuccessful){
               val body =response.body()
               if(body != null){
                   Result.success(body.map { it.toDomain() })
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