package com.android.whereareyou.login.presentation

import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext
import com.android.whereareyou.R
import com.android.whereareyou.WhereAreYouApplication
import com.android.whereareyou.core.data.api.interceptor.log.Logger
import com.android.whereareyou.core.presentation.Event
import com.android.whereareyou.core.util.hide
import com.android.whereareyou.core.util.moveScreen
import com.android.whereareyou.core.util.preference.PreferenceConstants
import com.android.whereareyou.core.util.preference.PreferenceHelper.set
import com.android.whereareyou.core.util.showToast
import com.android.whereareyou.login.data.LoginUiState
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.user.rx
import com.uber.autodispose.ScopeProvider
import com.uber.autodispose.autoDispose
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginStateHolder(val context : Context, private val scopeProvider: ScopeProvider) {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun kakaoLogin() {
        _uiState.update { it.copy(isLoading = true) }
        UserApiClient.rx.loginWithKakaoTalk(context)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {
                Logger.i("로그인 성공 ${it.accessToken}")
                //유저 정보 요청
                UserApiClient.rx.me()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
            .autoDispose(scopeProvider)
            .subscribe({ user ->
                Logger.i(
                    "사용자 정보 요청 성공" +
                            "\n회원 번호: ${user.id}" +
                            "\n닉네임: ${user.kakaoAccount?.profile?.nickname}"
                )
                if (user.id != null && user.kakaoAccount?.profile?.nickname != null) {
                    WhereAreYouApplication.prefs[PreferenceConstants.USER_ID] = user.id
                    WhereAreYouApplication.prefs[PreferenceConstants.NICKNAME] = user.kakaoAccount?.profile?.nickname
                    _uiState.update { it.copy(isUserLoggedIn = Event(true)) }
                }
                _uiState.update { it.copy(isLoading = false) }
            }, { error ->
                if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                    Logger.e(message = "로그인 실패 ${error.message}")
                } else {
                    Logger.e(message = "에러 로그: ${error.localizedMessage}")
                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정 연결 요청 메시지
                    showToast(
                        context,
                        context.getString(R.string.fragment_check_in_link_kakao_account)
                    )
                }
                _uiState.update { it.copy(isLoading = false) }
            })
    }


}