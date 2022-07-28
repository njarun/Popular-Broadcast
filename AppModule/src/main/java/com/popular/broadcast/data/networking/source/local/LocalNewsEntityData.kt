package com.popular.broadcast.data.networking.source.local

import com.popular.broadcast.data.database.dao.NewsDao
import com.popular.broadcast.data.dto.mapper.NewsEntityMapper.toNewsList
import com.popular.broadcast.data.dto.mapper.NewsMapper.toNewsEntities
import com.popular.broadcast.data.networking.source.NewsEntityData
import com.popular.broadcast.domain.dto.model.News
import com.popular.broadcast.domain.dto.model.NewsRequest
import java.util.*
import javax.inject.Inject

class LocalNewsEntityData @Inject constructor(private val newsDao: NewsDao) : NewsEntityData {

    override suspend fun getNews(newsRequest: NewsRequest): List<News> {

        val toDays = newsRequest.period

        val cal = Calendar.getInstance()
        cal.set(Calendar.DAY_OF_YEAR, -toDays)
        val toTimestamp = cal.time.time

        return newsDao.getNews(toTimestamp).toNewsList()
    }

    override suspend fun saveNews(newsList: List<News>) {

        clearNews()

        val newsEntities = newsList.toNewsEntities()
        newsDao.insert(newsEntities)
    }

    private fun clearNews() {
        newsDao.delete()
    }
}