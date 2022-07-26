package com.dch.aplayer.presentation.splash.binders

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.browser.customtabs.CustomTabsIntent
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
    Glide.with(context).load(R.mipmap.ic_launcher).into(this)
}

@BindingAdapter("on_click_open_custom_tab")
fun View.onClickOpenCustomTab(url: String) {

    setOnClickListener {

        try {

            val customTabsIntent : CustomTabsIntent = CustomTabsIntent.Builder().build()
            customTabsIntent.launchUrl(context, Uri.parse(url))
        }
        catch (ignored: Exception) {

            try {

                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(browserIntent)
            }
            catch (ignored2 : Exception) {

            }
        }
    }
}