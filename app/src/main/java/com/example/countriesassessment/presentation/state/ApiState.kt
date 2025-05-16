package com.example.countriesassessment.presentation.state

sealed class ApiState <out T>{
    object Loading: ApiState<Nothing>()
    data class Success<out T>(val data:T): ApiState<T>()
    data class Error(val error:String): ApiState<Nothing>()
}