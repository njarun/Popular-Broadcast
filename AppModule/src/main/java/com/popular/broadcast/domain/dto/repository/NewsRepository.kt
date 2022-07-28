package com.popular.broadcast.domain.dto.repository

import com.popular.broadcast.domain.dto.model.News
import com.popular.broadcast.domain.dto.model.NewsRequest

interface NewsRepository {

    suspend fun getNewsFromNetwork(newsRequest: NewsRequest): List<News>

    suspend fun getNewsFromLocal(newsRequest: NewsRequest): List<News>

    suspend fun saveNews(newsList: List<News>)
}