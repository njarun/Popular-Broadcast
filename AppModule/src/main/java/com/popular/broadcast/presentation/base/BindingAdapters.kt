package com.popular.broadcast.presentation.base

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.popular.broadcast.R
import com.popular.broadcast.presentation.base.list.BaseAdapter
import com.popular.broadcast.presentation.base.list.BaseListItem

@BindingAdapter("showToast")
fun View.showToast(message: String?) {

    message?.let {

        setOnClickListener {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}

@BindingAdapter("manageState")
fun SwipeRefreshLayout.manageState(state: Boolean) {
    isRefreshing = state
}

@BindingAdapter("setAdapter")
fun setAdapter(recyclerView: RecyclerView, adapter: BaseAdapter<ViewDataBinding, BaseListItem>?) {

    adapter?.let {
        recyclerView.adapter = it
    }
}

@BindingAdapter("setDataSet") @Suppress("UNCHECKED_CAST")
fun setDataSet(recyclerView: RecyclerView, list: List<BaseListItem>?) {
    val adapter = recyclerView.adapter as BaseAdapter<ViewDataBinding, BaseListItem>?
    adapter?.updateData(list ?: listOf())
}

@BindingAdapter("loadUrlOrPlaceholder")
fun ImageView.loadUrlOrPlaceholder(url: String?) {

    url?.let { Glide.with(context).load(url).into(this) } ?:
    Glide.with(context).load(R.mipmap.ic_launcher).into(this)
}

@BindingAdapter("openCustomTabOnClick")
fun View.openCustomTabOnClick(url: String) {

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