package com.android.whereareyou.core

import android.app.Application
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.*
import com.android.whereareyou.WhereAreYouApplication.Companion.application
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {


    val disposables = CompositeDisposable()
    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}