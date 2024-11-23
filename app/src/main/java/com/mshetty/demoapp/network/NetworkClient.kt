package com.mshetty.demoapp.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClient {

    private val BASEURL = "https://restcountries.com/"

    private fun getInterceptor() : Interceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        return httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private fun getOkHttpClient() : OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(getInterceptor()).build()
    }

    fun getRetrofitClient() : ApiInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASEURL)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: ApiInterface = retrofit.create(ApiInterface::class.java)
        return service
    }
}