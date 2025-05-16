package com.example.countriesassessment.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriesassessment.model.CountriesItem
import com.example.countriesassessment.remote.ApiState
import com.example.countriesassessment.usecases.GetCountriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(private val getCountriesUseCase: GetCountriesUseCase): ViewModel() {

    private  val _state = MutableStateFlow<ApiState<List<CountriesItem>>>(ApiState.Loading)
    val state : StateFlow<ApiState<List<CountriesItem>>> = _state

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