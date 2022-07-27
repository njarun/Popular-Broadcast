package com.popular.broadcast.data.schedule.mapper

import com.popular.broadcast.data.database.model.NewsEntity
import com.popular.broadcast.domain.schedule.model.News

object NewsMapper {

    fun List<News>.toNewsEntities(): List<NewsEntity> {

        val newsEntities = ArrayList<NewsEntity>()

        forEach {
            newsEntities.add(NewsEntity(it.id, it.url, it.title, it.summary, it.byline, it.section, it.updated, it.thumb, it.banner))
        }

        return newsEntities
    }
}