package com.example.countriesassessment.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.countriesassessment.presentation.viewmodel.CountriesViewModel
import com.example.countriesassessment.presentation.state.ApiState

@Composable
fun CountriesScreen(viewModel: CountriesViewModel= hiltViewModel()) {


    val countriesState = viewModel.state.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.fetchCountries()
    }

    when(countriesState){
        is ApiState.Loading -> {
            CircularProgressIndicator()
        }
        is ApiState.Success->{
            val response = countriesState.data
            LazyColumn {
                items (response){ country->
                    Card (modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp, vertical = 8.dp)){
                        Column (modifier = Modifier.fillMaxSize().padding(8.dp)){
                            Row (modifier = Modifier.fillMaxSize()){
                                Text(
                                    text = "${country.name}, ${country.region}",
                                    modifier = Modifier.weight(1f)
                                )
                                Text(text = country.code)
                            }

                            Text(text = " ")

                            Text(
                                text = country.capital,
                                modifier = Modifier.fillMaxSize()
                            )

                            Text(text = " ")

                        }
                    }
                }
            }
        }
        is ApiState.Error -> {
            Text(text = "Error: ${countriesState.error}")
        }
    }

}