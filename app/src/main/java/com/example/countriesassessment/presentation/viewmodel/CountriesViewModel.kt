package com.example.countriesassessment.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriesassessment.domain.data.CountryDomain
import com.example.countriesassessment.presentation.state.ApiState
import com.example.countriesassessment.domain.usecases.GetCountriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(private val getCountriesUseCase: GetCountriesUseCase): ViewModel() {

    private  val _state = MutableStateFlow<ApiState<List<CountryDomain>>>(ApiState.Loading)
    val state : StateFlow<ApiState<List<CountryDomain>>> = _state

    fun fetchCountries(){
        viewModelScope.launch (Dispatchers.IO){
         getCountriesUseCase().collect{ data->
             data.fold(
                 onSuccess = { result->
                     _state.value = ApiState.Success(result)
                 },
                 onFailure = { error->
                     _state.value = ApiState.Error(error.message?: "Unknown error")
                 }
             )
         }
        }
    }
}