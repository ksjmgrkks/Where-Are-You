package com.android.whereareyou.sign.presentation.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.whereareyou.R
import com.android.whereareyou.core.BaseFragment
import com.android.whereareyou.core.MainViewModel
import com.android.whereareyou.core.util.moveScreen
import com.android.whereareyou.databinding.FragmentSignInBinding
import dagger.hilt.android.AndroidEntryPoint

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
    }

    private fun setupUI() {
        activityViewModel.toolBarVisibility.value = false
        activityViewModel.fabVisibility.value = false

        binding.imageViewKakaoLogin.setOnClickListener {
            moveScreen(R.id.action_sign_in_to_weekly_schedule)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}