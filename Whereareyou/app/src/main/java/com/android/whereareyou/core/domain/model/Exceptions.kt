package com.android.whereareyou.core.domain.model

import android.content.res.Resources
import com.android.whereareyou.R
import java.io.IOException


class NoMoreMoviesException(message: String): Exception(message)

class NetworkUnavailableException(message: String = Resources.getSystem().getString(R.string.network_unavailable_exception)) : IOException(message)

class NetworkException(message: String): Exception(message)