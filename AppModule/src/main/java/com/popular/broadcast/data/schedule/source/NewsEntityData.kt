package com.popular.broadcast.data.schedule.source

import com.popular.broadcast.domain.schedule.model.News
import com.popular.broadcast.domain.schedule.model.NewsRequest

interface NewsEntityData {

    suspend fun getNews(newsRequest: NewsRequest): List<News>

    suspend fun saveNews(newsList: List<News>)
}