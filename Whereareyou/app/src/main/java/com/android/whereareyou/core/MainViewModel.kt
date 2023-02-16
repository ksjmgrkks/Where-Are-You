package com.android.whereareyou.core

import android.opengl.Visibility
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
) : ViewModel() {
    private val _toolBarVisibility = MutableLiveData(false)
    val toolBarVisibility: LiveData<Boolean>
        get() = _toolBarVisibility

    private val _fabVisibility = MutableLiveData(false)
    val fabVisibility: LiveData<Boolean>
        get() = _fabVisibility
    init {

    }

    fun settingUI(visibility: Boolean){
        _toolBarVisibility.postValue(visibility)
        _fabVisibility.postValue(visibility)
    }
    override fun onCleared() {
        super.onCleared()
    }
}