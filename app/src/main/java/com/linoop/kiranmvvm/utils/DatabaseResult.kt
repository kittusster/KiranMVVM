package com.linoop.kiranmvvm.utils

sealed class DatabaseResult<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?, message: String?) : DatabaseResult<T>(data, message)
    class Error<T>(message: String?, data: T? = null) : DatabaseResult<T>(data, message)
    class Loading<T> : DatabaseResult<T>()
}