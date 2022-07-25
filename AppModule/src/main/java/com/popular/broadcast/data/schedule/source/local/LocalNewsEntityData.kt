package com.popular.broadcast.data.schedule.source.local

import com.popular.broadcast.data.database.dao.NewsDao
import com.popular.broadcast.data.schedule.mapper.NewsEntityMapper.toNewsList
import com.popular.broadcast.data.schedule.mapper.NewsMapper.toNewsEntities
import com.popular.broadcast.data.schedule.source.NewsEntityData
import com.popular.broadcast.domain.schedule.model.News
import com.popular.broadcast.domain.schedule.model.NewsRequest
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

        val newsEntities = newsList.toNewsEntities()
        newsDao.insert(newsEntities)
    }
}