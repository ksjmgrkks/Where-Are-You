package com.android.whereareyou.signin.presentation

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.android.whereareyou.WhereAreYouApplication.Companion.application
import com.android.whereareyou.core.BaseViewModel
import com.android.whereareyou.core.data.api.interceptor.log.Logger
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.user.rx
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
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