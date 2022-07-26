package com.popular.broadcast.domain.schedule.usecase

import com.popular.broadcast.domain.schedule.model.News
import com.popular.broadcast.domain.schedule.model.NewsRequest
import com.popular.broadcast.domain.schedule.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNews @Inject constructor(private val newsRepository: NewsRepository) {

    suspend fun collectNews(request: NewsRequest): Flow<Any> = flow {

        val localNews = getNewsFromLocal(request)

        localNews.isNotEmpty().run { emit(localNews) }.also {

            //emit(UiState.Loading)

            val networkNews = getNewsFromNetwork(request)

            newsRepository.saveNews(networkNews)

            emit(networkNews)
        }
    }

    private suspend fun getNewsFromLocal(param: NewsRequest): List<News> {
        return newsRepository.getNewsFromLocal(param)
    }

    private suspend fun getNewsFromNetwork(param: NewsRequest): List<News> {
        return newsRepository.getNewsFromNetwork(param)
    }
}