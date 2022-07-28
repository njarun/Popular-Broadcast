package com.popular.broadcast.data.networking.source

import com.popular.broadcast.domain.dto.model.News
import com.popular.broadcast.domain.dto.model.NewsRequest

interface NewsEntityData {

    suspend fun getNews(newsRequest: NewsRequest): List<News>

    suspend fun saveNews(newsList: List<News>)
}