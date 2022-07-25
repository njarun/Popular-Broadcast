package com.popular.broadcast.domain.schedule.usecase

import com.popular.broadcast.domain.schedule.model.News
import com.popular.broadcast.domain.schedule.model.NewsRequest
import com.popular.broadcast.domain.schedule.repository.NewsRepository
import javax.inject.Inject

class GetNews @Inject constructor(private val newsRepository: NewsRepository) {

    suspend fun execute(request: NewsRequest): List<News> {

        return getNewsFromLocal(request).also {

            val newsList = getNewsFromNetwork(request)

            newsRepository.saveNews(newsList)

            getNewsFromLocal(request)
        }
    }

    private suspend fun getNewsFromLocal(param: NewsRequest): List<News> {
        return newsRepository.getNewsFromLocal(param)
    }

    private suspend fun getNewsFromNetwork(param: NewsRequest): List<News> {
        return newsRepository.getNewsFromNetwork(param)
    }
}