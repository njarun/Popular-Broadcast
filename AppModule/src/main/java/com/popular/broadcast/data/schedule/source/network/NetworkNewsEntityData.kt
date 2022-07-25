package com.popular.broadcast.data.schedule.source.network

import com.popular.broadcast.data.schedule.api.NewsApi
import com.popular.broadcast.data.schedule.mapper.NewsResponseMapper.toNewsList
import com.popular.broadcast.data.schedule.source.NewsEntityData
import com.popular.broadcast.domain.schedule.model.News
import com.popular.broadcast.domain.schedule.model.NewsRequest
import javax.inject.Inject

class NetworkNewsEntityData @Inject constructor(private val newsApi: NewsApi) : NewsEntityData {

    override suspend fun getNews(newsRequest: NewsRequest): List<News> {
        return newsApi.getNews(newsRequest.section, newsRequest.period).toNewsList()
    }

    override suspend fun saveNews(newsList: List<News>) {
        //no operation
    }
}