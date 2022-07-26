package com.popular.broadcast.presentation.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.popular.broadcast.databinding.LayoutNewsItemBinding
import com.popular.broadcast.domain.schedule.model.News

class NewsViewHolder(private val binding: LayoutNewsItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(news: News) {
        binding.news = news
    }
}