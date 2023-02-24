package com.android.whereareyou.core

import android.opengl.Visibility
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
) : BaseViewModel() {
    init {

    }
    override fun onCleared() {
        super.onCleared()
    }
}