package com.popular.broadcast.data.schedule.mapper

import com.popular.broadcast.data.database.model.NewsEntity
import com.popular.broadcast.domain.schedule.model.News

object NewsEntityMapper {

    fun List<NewsEntity>.toNewsList(): List<News> {

        val newsList = ArrayList<News>()

        forEach {
            newsList.add(News(it.id, it.url, it.title, it.summary, it.byline, it.section, it.updated, it.thumb))
        }

        return newsList
    }
}