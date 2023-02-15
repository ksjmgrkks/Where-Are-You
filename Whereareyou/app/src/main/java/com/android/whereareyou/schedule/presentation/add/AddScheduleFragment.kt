package com.android.whereareyou.schedule.presentation.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.adapters.ViewBindingAdapter.setClickListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.android.whereareyou.R
import com.android.whereareyou.databinding.FragmentAddScheduleBinding
import com.android.whereareyou.databinding.FragmentDailyScheduleBinding
import com.android.whereareyou.databinding.FragmentWeeklyScheduleBinding
import com.android.whereareyou.schedule.presentation.daily.DailyScheduleViewModel
import com.android.whereareyou.schedule.presentation.weekly.WeeklyScheduleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddScheduleFragment : Fragment() {
    private var _binding: FragmentAddScheduleBinding? = null
    private val binding
        get() = requireNotNull(_binding)

    private val viewModel: AddScheduleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_schedule, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}