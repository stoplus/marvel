package com.smart.utils

sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error<T>(val error: Throwable) : Result<T>()
}

fun <T> Result<T>.wrap(onSuccess: ((T) -> Unit)? = null, onError: ((Throwable) -> Unit)? = null) {
    when (this) {
        is Result.Success -> onSuccess?.invoke(data)
        is Result.Error -> onError?.invoke(error)
    }
}

suspend fun <T> wrapSafe(operation: suspend () -> T): Result<T> {
    return try {
        Result.Success(operation())
    } catch (e: Exception) {
        Result.Error(e)
    }
}
