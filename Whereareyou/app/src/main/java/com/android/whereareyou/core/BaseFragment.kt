package com.android.whereareyou.core

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment : Fragment(){
    val activityViewModel: MainViewModel by activityViewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T =
                MainViewModel() as T
        }
    }
}