package com.android.whereareyou.signin.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.android.whereareyou.R
import com.android.whereareyou.WhereAreYouApplication
import com.android.whereareyou.core.BaseFragment
import com.android.whereareyou.core.data.api.interceptor.log.Logger
import com.android.whereareyou.core.util.hide
import com.android.whereareyou.core.util.moveScreen
import com.android.whereareyou.core.util.preference.PreferenceConstants
import com.android.whereareyou.core.util.preference.PreferenceHelper.set
import com.android.whereareyou.core.util.show
import com.android.whereareyou.core.util.showToast
import com.android.whereareyou.databinding.FragmentSignInBinding
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.user.rx
import com.uber.autodispose.ScopeProvider
import com.uber.autodispose.autoDispose
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


@AndroidEntryPoint
class SignInFragment : BaseFragment() {
    private val scopeProvider = ScopeProvider.UNBOUND
    private var _binding: FragmentSignInBinding? = null
    private val binding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        //키해시 구하기
        //Logger.i("keyhash: ${Utility.getKeyHash(requireContext())}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupUI() {
        activityViewModel.settingUI(false)
        binding.imageViewKakaoLogin.setOnClickListener {
            requireContext().run {
                binding.progressBar.show()
                // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오 설치 유도 토스트 메시지
                if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                    kakaoLogin(this)
                } else {
                    showToast(this, getString(R.string.fragment_check_in_install_kakao))
                    binding.progressBar.hide()
                }
            }
        }
    }

    private fun kakaoLogin(context: Context) =
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
                    WhereAreYouApplication.prefs[PreferenceConstants.NICKNAME] =
                        user.kakaoAccount?.profile?.nickname
                    moveScreen(R.id.action_sign_in_to_weekly_schedule)
                }
                binding.progressBar.hide()
            }, { error ->
                if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                    Logger.e(message = "로그인 실패 ${error.message}")
                } else {
                    Logger.e(message = "에러 로그: ${error.localizedMessage}")
                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정 연결 요청 메시지
                    showToast(
                        requireContext(),
                        getString(R.string.fragment_check_in_link_kakao_account)
                    )
                }
                binding.progressBar.hide()
            })
}