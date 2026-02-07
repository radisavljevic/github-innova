package com.example.githubinnova.core.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

fun <T> safeApiCall(block: suspend () -> T): Flow<ApiResult<T>> = flow {
    emit(ApiResult.Success(block()) as ApiResult<T>)
}.catch { e ->
    emit(ApiResult.Failure(e) as ApiResult<T>)
}