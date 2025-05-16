package com.example.countriesassessment.di

import com.example.countriesassessment.remote.ApiService
import com.example.countriesassessment.repository.CountryRepository
import com.example.countriesassessment.repository.CountryRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.math.log


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitClient(): Retrofit{
        val loggingInterceptor= HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient= OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

        return Retrofit.Builder().apply {
            baseUrl("https://gist.githubusercontent.com/")
            addConverterFactory(GsonConverterFactory.create())
            client(okHttpClient)

        }.build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService{
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService): CountryRepository{
        return CountryRepositoryImpl(apiService)
    }
}