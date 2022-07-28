package com.popular.broadcast.domain.usecase

import com.popular.broadcast.data.networking.CoroutineDispatcherProvider
import com.popular.broadcast.domain.dto.model.News
import com.popular.broadcast.domain.dto.model.NewsRequest
import com.popular.broadcast.domain.dto.repository.NewsRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetNews @Inject constructor(
    private val newsRepository: NewsRepository,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
) {

    fun fetchNews(request: NewsRequest) = flow {

        val localNews = getNewsFromLocal(request)

        if (localNews.isNotEmpty()) {
            emit(localNews)
        }

        emit(true) //to show the fetch progress

        val networkNews = getNewsFromNetwork(request)

        if (networkNews.isNotEmpty()) { //Else it will be network or data error, which will be caught in the VM
            newsRepository.saveNews(networkNews)
            emit(networkNews)
        }
    }
        .flowOn(coroutineDispatcherProvider.IO())

    private suspend fun getNewsFromLocal(param: NewsRequest): List<News> {
        return newsRepository.getNewsFromLocal(param)
    }

    private suspend fun getNewsFromNetwork(param: NewsRequest): List<News> {
        return newsRepository.getNewsFromNetwork(param)
    }
}