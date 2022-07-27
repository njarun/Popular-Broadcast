package com.popular.broadcast.domain.schedule.usecase

import com.popular.broadcast.domain.schedule.model.News
import com.popular.broadcast.domain.schedule.model.NewsRequest
import com.popular.broadcast.domain.schedule.repository.NewsRepository
import com.popular.broadcast.presentation.base.state.UiState
import com.popular.broadcast.util.ExceptionParser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNews @Inject constructor(private val newsRepository: NewsRepository) {

    suspend fun collectNews(request: NewsRequest): Flow<Any> = flow {

        val localNews = getNewsFromLocal(request)

        if(localNews.isNotEmpty()) {
            emit(localNews)
        }
        else emit(UiState.Loading)

        try {

            val networkNews = getNewsFromNetwork(request)

            if(networkNews.isNotEmpty()) {

                newsRepository.saveNews(networkNews)

                emit(networkNews)
            }
        }
        catch (e: Exception) {

            e.printStackTrace()

            if(localNews.isEmpty())
                emit(UiState.Error(ExceptionParser.getMessage(e)))
        }
    }

    private suspend fun getNewsFromLocal(param: NewsRequest): List<News> {
        return newsRepository.getNewsFromLocal(param)
    }

    private suspend fun getNewsFromNetwork(param: NewsRequest): List<News> {
        return newsRepository.getNewsFromNetwork(param)
    }
}