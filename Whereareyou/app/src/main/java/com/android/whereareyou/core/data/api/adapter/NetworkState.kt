package com.android.whereareyou.core.data.api.adapter

import java.io.IOException

sealed class NetworkState<out T : Any> {

    // 200대 응답 성공
    data class Success<T : Any>(val body: T) : NetworkState<T>()

    // isSuccessful == false (200~300대 응답이 아님)
    data class Failure(val code: Int, val error: String?) : NetworkState<Nothing>()

    // onFailure (Network Error,timeout)
    data class NetworkError(val error: IOException) : NetworkState<Nothing>()

    // 기타 모든 오류 처리
    data class UnknownError(val t: Throwable?, val errorState: String) : NetworkState<Nothing>()
}
