package com.popular.broadcast.domain.schedule.repository

import com.popular.broadcast.domain.schedule.model.News
import com.popular.broadcast.domain.schedule.model.NewsRequest

interface NewsRepository {

    suspend fun getNewsFromNetwork(newsRequest: NewsRequest): List<News>

    suspend fun getNewsFromLocal(newsRequest: NewsRequest): List<News>

    suspend fun saveNews(newsList: List<News>)
}