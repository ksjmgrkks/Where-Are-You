package com.android.whereareyou.signin.presentation

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.android.whereareyou.R
import com.android.whereareyou.core.BaseFragment
import com.android.whereareyou.core.data.api.interceptor.log.Logger
import com.android.whereareyou.core.util.moveScreen
import com.android.whereareyou.databinding.FragmentSignInBinding
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.user.rx
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo

@AndroidEntryPoint
class SignInFragment : BaseFragment() {
    private var _binding: FragmentSignInBinding? = null
    private val binding
        get() = requireNotNull(_binding)

    private val viewModel: SignInViewModel by viewModels()
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
        setObserver()
        //키해시 구하기
        //Logger.i("keyhash: ${Utility.getKeyHash(requireContext())}")
    }

    private fun setupUI() {
        activityViewModel.settingUI(false)
        binding.imageViewKakaoLogin.setOnClickListener {
            viewModel.kakaoLogIn(UserApiClient.rx.loginWithKakaoTalk(requireContext()))
        }
    }

    private fun setObserver() {
        viewModel.moveScreen.observe(viewLifecycleOwner) { event ->
            event?.run { getContentIfNotHandled()?.let { moveScreen(it) } }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.disposables.clear()
        _binding = null
    }
}