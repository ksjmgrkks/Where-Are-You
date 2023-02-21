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
    private val _fabVisibility = MutableLiveData(false)
    val fabVisibility: LiveData<Boolean>
        get() = _fabVisibility
    init {

    }
    fun settingUI(visibility: Boolean) = _fabVisibility.postValue(visibility)
    override fun onCleared() {
        super.onCleared()
    }
}