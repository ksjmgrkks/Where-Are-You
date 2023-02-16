package com.android.whereareyou.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
) : ViewModel() {
    val toolBarVisibility = MutableLiveData(false)
    val fabVisibility = MutableLiveData(false)
    init {

    }
    override fun onCleared() {
        super.onCleared()
    }
}