package com.mshetty.demoapp.model

sealed class NetworkState<Any>(val data:Any?= null, val messge:String?= null) {

    class  SUCCESS<Any>(data: Any?) : NetworkState<Any>(data)
    class  LOADING<Any> : NetworkState<Any>()
    class  ERROR<Any>(messge: String?) : NetworkState<Any>(messge = messge )

}