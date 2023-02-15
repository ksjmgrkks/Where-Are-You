package com.android.whereareyou.core.presentation.adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.android.whereareyou.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


@BindingAdapter(value = ["setVisible"])
fun setVisible(view: View, visible: Boolean) {
    if(visible){
        view.visibility = View.VISIBLE
    }else{
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["imageUrl"])
fun setImageUrl(view: ImageView, imageUrl: String) {
    Glide.with(view.context)
        .load(imageUrl.ifEmpty { null })
        .error(R.drawable.img_placeholder)
        .centerCrop()
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(view)
}