package com.android.whereareyou.login.data

import com.android.whereareyou.core.presentation.Event

data class LoginUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isUserLoggedIn: Event<Boolean> = Event(false)
)
