package com.android.whereareyou.signin.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.whereareyou.core.BaseViewModel
import com.android.whereareyou.core.presentation.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
) : BaseViewModel() {
    init {

    }
    override fun onCleared() {
        super.onCleared()
    }
}