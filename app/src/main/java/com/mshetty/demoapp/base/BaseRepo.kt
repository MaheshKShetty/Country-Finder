package com.mshetty.demoapp.base

import com.mshetty.demoapp.model.NetworkState
import retrofit2.Response

open class BaseRepo {

    suspend fun <Any> safeApiCall(apicall : suspend () -> Response<Any>) : NetworkState<Any> {
        try {
            NetworkState.LOADING<Any>()
            val response = apicall()
            if (response.isSuccessful) {
                return NetworkState.SUCCESS(response.body())
            } else {
                return NetworkState.ERROR(response.message())
            }
        } catch (e:Exception) {
            e.printStackTrace()
            return NetworkState.ERROR(e.printStackTrace().toString())
        }
    }
}