package com.mshetty.demoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mshetty.demoapp.model.CountryResponse
import com.mshetty.demoapp.model.CountryResponseItem
import com.mshetty.demoapp.model.NetworkState
import com.mshetty.demoapp.network.CountryRepo
import kotlinx.coroutines.launch

class CountryViewModel(context:Application) : AndroidViewModel(context) {

    val repo: CountryRepo = CountryRepo()
    val countriesLiveData: MutableLiveData<NetworkState<List<CountryResponseItem?>?>> = repo.countriesLiveData

    fun fetchCountries() {
        viewModelScope.launch {
            repo.fetchCountries()
        }
    }
}