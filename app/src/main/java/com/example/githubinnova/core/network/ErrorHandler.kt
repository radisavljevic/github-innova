package com.example.githubinnova.core.network

import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ErrorHandler @Inject constructor() {
    fun handle(throwable: Throwable): String {
        return when (throwable) {
            is IOException -> "Network error"
            is HttpException -> "Server error ${throwable.code()}"
            else -> "Unknown error"
        }
    }
}