package com.cpfit.examplehiltkotlin.utils

//คล้าย enum แต่ return เป็น  class
sealed class NetworkResult<T>(val data: T? = null, val error: String? = null) {
    class Success<T>(data: T) : NetworkResult<T>(data)
    class Error<T>(error: String?, data: T? = null) : NetworkResult<T>(data, error)
    class Loading<T> : NetworkResult<T>()
}