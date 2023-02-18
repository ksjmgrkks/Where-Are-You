package com.android.whereareyou.signin.presentation

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.whereareyou.R
import com.android.whereareyou.WhereAreYouApplication.Companion.application
import com.android.whereareyou.core.BaseViewModel
import com.android.whereareyou.core.data.api.interceptor.log.Logger
import com.android.whereareyou.core.presentation.Event
import com.android.whereareyou.core.util.moveScreen
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.user.rx
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
) : BaseViewModel() {
    private val _moveScreen = MutableLiveData(Event(0))
    val moveScreen: LiveData<Event<Int>>
        get() = _moveScreen

    init {

    }

    fun kakaoLogIn(single: Single<OAuthToken>) =
        single.observeOn(AndroidSchedulers.mainThread())
            .subscribe({ token ->
                Logger.i("로그인 성공 ${token.accessToken}")
                _moveScreen.postValue(Event(R.id.action_sign_in_to_weekly_schedule))
            }, { error ->
                Logger.i("로그인 실패 $error")
            })
            .addTo(disposables)


    override fun onCleared() {
        super.onCleared()
    }
}