package com.popular.broadcast.data.schedule.repository

import com.popular.broadcast.data.schedule.factory.NewsFactory
import com.popular.broadcast.domain.schedule.model.News
import com.popular.broadcast.domain.schedule.model.NewsRequest
import com.popular.broadcast.domain.schedule.repository.NewsRepository
import com.popular.broadcast.util.Source
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val newsFactory: NewsFactory): NewsRepository {

    override suspend fun getNewsFromNetwork(newsRequest: NewsRequest): List<News> {
        return newsFactory.create(Source.NETWORK).getNews(newsRequest)
    }

    override suspend fun getNewsFromLocal(newsRequest: NewsRequest): List<News> {
        return newsFactory.create(Source.LOCAL).getNews(newsRequest)
    }

    override suspend fun saveNews(newsList: List<News>) {
        newsFactory.create(Source.LOCAL).saveNews(newsList)
    }
}