package com.mshetty.demoapp.network

import androidx.lifecycle.MutableLiveData
import com.mshetty.demoapp.base.BaseRepo
import com.mshetty.demoapp.model.CountryResponse
import com.mshetty.demoapp.model.CountryResponseItem
import com.mshetty.demoapp.model.NetworkState

class CountryRepo  : BaseRepo() {

  val countriesLiveData: MutableLiveData<NetworkState<List<CountryResponseItem?>?>>  = MutableLiveData()

  suspend fun fetchCountries()  {
      val response = safeApiCall { NetworkClient.getRetrofitClient().fetchCountries() }
      countriesLiveData.postValue(response)
  }
}