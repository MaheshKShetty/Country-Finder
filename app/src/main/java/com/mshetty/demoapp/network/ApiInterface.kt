package com.mshetty.demoapp.network

import com.mshetty.demoapp.model.CountryResponse
import com.mshetty.demoapp.model.CountryResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("v3.1/subregion/asia")
    suspend fun fetchCountries() : Response<List<CountryResponseItem?>?>
}