package com.dch.aplayer.presentation.splash.binders

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.popular.broadcast.R

@BindingAdapter("android:visibility")
fun View.setVisibility(visible: Boolean) {

    visibility = if (visible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter("load_url_or_placeholder")
fun ImageView.loadUrlOrPlaceholder(url: String?) {

    url?.let { Glide.with(context).load(url).into(this) } ?:
    Glide.with(context).load(R.mipmap.ic_launcher_round).into(this)
}