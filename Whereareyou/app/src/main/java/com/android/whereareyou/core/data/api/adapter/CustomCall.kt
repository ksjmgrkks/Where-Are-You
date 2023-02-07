package com.android.whereareyou.core.data.api.adapter

import android.content.res.Resources
import com.android.whereareyou.R
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class CustomCall<T : Any>(private val call: Call<T>) : Call<NetworkState<T>> {

    override fun enqueue(callback: Callback<NetworkState<T>>) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body = response.body()
                val code = response.code()
                val error = response.errorBody()?.string()

                if (response.isSuccessful) {
                    if (body != null) {
                        callback.onResponse(
                            this@CustomCall, Response.success(NetworkState.Success(body))
                        )
                    } else {
                        callback.onResponse(
                            this@CustomCall,
                            Response.success(
                                NetworkState.UnknownError(
                                    IllegalStateException(Resources.getSystem().getString(R.string.on_response_network_state_unknown_error)),
                                    Resources.getSystem().getString(R.string.on_response_network_state_unknown_error)
                                )
                            )
                        )
                    }
                } else {
                    callback.onResponse(
                        this@CustomCall,
                        Response.success(NetworkState.Failure(code, error))
                    )
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val errorResponse = when (t) {
                    is IOException -> NetworkState.NetworkError(t)
                    else -> NetworkState.UnknownError(t, Resources.getSystem().getString(R.string.on_failure_network_state_unknown_error))
                }
                callback.onResponse(this@CustomCall, Response.success(errorResponse))
            }
        })
    }

    override fun clone(): Call<NetworkState<T>> = CustomCall(call.clone())

    override fun execute(): Response<NetworkState<T>> {
        throw UnsupportedOperationException(Resources.getSystem().getString(R.string.unsupported_operation_exception))
    }

    override fun isExecuted(): Boolean = call.isExecuted

    override fun cancel() = call.cancel()

    override fun isCanceled(): Boolean = call.isCanceled

    override fun request(): Request = call.request()

    override fun timeout(): Timeout = call.timeout()
}
