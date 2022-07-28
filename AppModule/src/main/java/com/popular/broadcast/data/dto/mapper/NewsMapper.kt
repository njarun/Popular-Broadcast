package com.popular.broadcast.data.dto.mapper

import com.popular.broadcast.data.database.model.NewsEntity
import com.popular.broadcast.domain.dto.model.News

object NewsMapper {

    fun List<News>.toNewsEntities(): List<NewsEntity> {

        val newsEntities = ArrayList<NewsEntity>()

        forEach {
            newsEntities.add(toNewsEntity(it))
        }

        return newsEntities
    }

    fun toNewsEntity(it: News): NewsEntity {
        return NewsEntity(
            it.id,
            it.url,
            it.title,
            it.summary,
            it.byline,
            it.section,
            it.updated,
            it.thumb,
            it.banner
        )
    }
}