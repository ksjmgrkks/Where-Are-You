package com.android.whereareyou.core.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.android.whereareyou.R
import com.android.whereareyou.core.data.api.interceptor.log.Logger
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun ImageView.setImage(url: String) {
    Glide.with(this.context)
        .load(url.ifEmpty { null })
        .error(R.drawable.img_placeholder)
        .centerCrop()
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

inline fun CoroutineScope.createExceptionHandler(
    message: String,
    crossinline action: (throwable: Throwable) -> Unit
) = CoroutineExceptionHandler { _, throwable ->
    Logger.e(throwable, message)
    throwable.printStackTrace()

    launch {
        action(throwable)
    }
}

fun FragmentActivity.getForegroundFragment(): Fragment? = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)?.childFragmentManager?.fragments?.get(0)
fun FragmentActivity.moveScreen(id: Int) = Navigation.findNavController(this, R.id.nav_host_fragment).navigate(id)
fun Fragment.moveScreen(id: Int) = findNavController().navigate(id)
internal fun View.backgroundDrawable(@DrawableRes drawable: Int) { background = context.drawableRes(drawable) }
internal fun Context.drawableRes(@DrawableRes drawable: Int) = ContextCompat.getDrawable(this, drawable)
internal fun View.backgroundColor(@ColorRes color: Int) = setBackgroundColor(context.getColorCompat(color))
internal fun Context.getColorCompat(@ColorRes color: Int) = ContextCompat.getColor(this, color)
internal fun TextView.textColor(@ColorRes color: Int) = setTextColor(context.getColorCompat(color))