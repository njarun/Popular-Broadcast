package com.popular.broadcast.data.dto.mapper

import com.popular.broadcast.data.database.model.NewsEntity
import com.popular.broadcast.domain.dto.model.News

object NewsEntityMapper {

    fun List<NewsEntity>.toNewsList(): List<News> {

        val newsList = ArrayList<News>()

        forEach {
            newsList.add(
                News(
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
            )
        }

        return newsList
    }
}