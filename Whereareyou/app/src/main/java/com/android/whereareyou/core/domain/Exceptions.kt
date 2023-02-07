package com.android.whereareyou.core.domain

import java.io.IOException


class NoMoreMoviesException(message: String): Exception(message)

class NetworkUnavailableException(message: String = "사용 가능한 네트워크가 없습니다.") : IOException(message)

class NetworkException(message: String): Exception(message)