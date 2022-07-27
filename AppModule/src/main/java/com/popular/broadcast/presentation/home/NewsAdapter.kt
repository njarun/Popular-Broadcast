package com.popular.broadcast.presentation.home

import com.popular.broadcast.R
import com.popular.broadcast.databinding.LayoutNewsItemBinding
import com.popular.broadcast.domain.schedule.model.News
import com.popular.broadcast.presentation.base.list.BaseAdapter

class NewsAdapterNew(list: List<News>, private val newsListener: NewsListener)
    : BaseAdapter<LayoutNewsItemBinding, News>(list) {

    override val layoutId: Int = R.layout.layout_news_item

    override fun bind(binding: LayoutNewsItemBinding, item: News) {

        binding.apply {

            news = item
            listener = newsListener
            executePendingBindings()
        }
    }
}

interface NewsListener {
    fun onNewsClicked(news: News)
}