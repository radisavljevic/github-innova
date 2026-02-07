package com.example.githubinnova.core.network

sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Failure(val throwable: Throwable) : ApiResult<Nothing>()
}