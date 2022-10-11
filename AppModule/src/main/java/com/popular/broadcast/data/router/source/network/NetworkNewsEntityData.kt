package com.popular.broadcast.data.router.source.network

import com.popular.broadcast.data.dto.mapper.NewsResponseMapper.toNewsList
import com.popular.broadcast.data.router.api.NewsApi
import com.popular.broadcast.data.router.source.NewsEntityData
import com.popular.broadcast.domain.dto.model.News
import com.popular.broadcast.domain.dto.model.NewsRequest
import javax.inject.Inject

class NetworkNewsEntityData @Inject constructor(private val newsApi: NewsApi) : NewsEntityData {

    override suspend fun getNews(newsRequest: NewsRequest): List<News> {
        return newsApi.getNews(newsRequest.section, newsRequest.period).toNewsList()
    }
}