package com.example.githubinnova.core.network

interface ErrorHandler {
    fun handle(throwable: Throwable): String
}