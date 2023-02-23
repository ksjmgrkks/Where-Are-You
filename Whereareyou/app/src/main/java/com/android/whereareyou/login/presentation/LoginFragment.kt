package com.android.whereareyou.login.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.android.whereareyou.R
import com.android.whereareyou.core.BaseFragment
import com.android.whereareyou.core.util.hide
import com.android.whereareyou.core.util.moveScreen
import com.android.whereareyou.core.util.show
import com.android.whereareyou.core.util.showToast
import com.android.whereareyou.databinding.FragmentLoginBinding
import com.kakao.sdk.user.UserApiClient
import com.uber.autodispose.ScopeProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginFragment : BaseFragment() {
    private val scopeProvider = ScopeProvider.UNBOUND
    private val signInStateHolder: LoginStateHolder by lazy {
        LoginStateHolder(requireContext(), scopeProvider)
    }
    private var _binding: FragmentLoginBinding? = null
    private val binding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //키해시 구하기
        //Logger.i("keyhash: ${Utility.getKeyHash(requireContext())}")
        setupUI()
        //uiState Observer
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                signInStateHolder.uiState.collect { uiState ->
                    //유저가 로그인 되었을 때
                    uiState.isUserLoggedIn.getContentIfNotHandled()?.run {
                        if (this)
                            moveScreen(R.id.action_login_to_weekly_schedule)
                    }
                    //로딩바 표시 여부
                    if (uiState.isLoading) {
                        binding.progressBar.show()
                    } else {
                        binding.progressBar.hide()
                    }
                    //에러 메시지
                    if (!uiState.errorMessage.isNullOrEmpty()) {
                        showToast(requireContext(), uiState.errorMessage)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupUI() {
        activityViewModel.settingUI(false)
        binding.imageViewKakaoLogin.setOnClickListener {
            requireContext().run {
                // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오 설치 유도 토스트 메시지
                if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                    signInStateHolder.kakaoLogin()
                } else {
                    showToast(this, getString(R.string.fragment_check_in_install_kakao))
                }
            }
        }
    }
}