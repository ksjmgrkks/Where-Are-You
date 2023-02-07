package com.android.whereareyou.core.data.api.interceptor

import com.android.whereareyou.core.data.api.interceptor.log.Logger
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject

class LoggingInterceptor @Inject constructor() : HttpLoggingInterceptor.Logger {
  override fun log(message: String) {
    Logger.i(message)
  }
}