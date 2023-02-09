package com.android.whereareyou.core.presentation.model.mapper

interface UiMapper<E, V> {

  fun mapToView(input: E): V
}