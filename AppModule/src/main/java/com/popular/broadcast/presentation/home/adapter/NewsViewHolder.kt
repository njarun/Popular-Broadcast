package com.popular.broadcast.presentation.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.popular.broadcast.databinding.LayoutNewsItemBinding
import com.popular.broadcast.domain.schedule.model.News
import com.popular.broadcast.presentation.base.handler.AppInterface

class NewsViewHolder(private val binding: LayoutNewsItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(appInterface: AppInterface, news: News) {

        binding.callback = appInterface
        binding.news = news
    }
}