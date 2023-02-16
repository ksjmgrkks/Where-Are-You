package com.android.whereareyou.schedule.presentation.weekly

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
import com.android.whereareyou.R
import com.android.whereareyou.core.BaseFragment
import com.android.whereareyou.core.MainViewModel
import com.android.whereareyou.databinding.FragmentWeeklyScheduleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeeklyScheduleFragment : BaseFragment() {
    private var _binding: FragmentWeeklyScheduleBinding? = null
    private val binding
        get() = requireNotNull(_binding)

    private val viewModel: WeeklyScheduleViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_weekly_schedule, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        activityViewModel.settingUI(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}