package com.popular.broadcast.presentation.detail.binders

import android.webkit.WebView
import androidx.databinding.BindingAdapter

@BindingAdapter("loadUrl")
fun WebView.setUrl(url: String) {

    this.settings.javaScriptEnabled = true
    this.loadUrl(url)
}