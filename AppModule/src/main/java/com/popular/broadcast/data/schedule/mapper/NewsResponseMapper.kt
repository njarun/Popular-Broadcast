package com.popular.broadcast.data.schedule.mapper

import com.popular.broadcast.data.schedule.model.NewsResponse
import com.popular.broadcast.domain.schedule.model.News
import com.popular.broadcast.util.TimeUtil

object NewsResponseMapper {

    fun NewsResponse.toNewsList(): List<News> {

        val newsList = ArrayList<News>()
        val publishedFormat = "yyyy-MM-dd" //"2022-07-20"
        val updatedFormat = "yyyy-MM-dd HH:mm:ss" //2022-07-22 11:33:36

        results?.forEach { result ->

            try {

                val date = TimeUtil.getTimestamp(
                    updatedFormat,
                    result.updated,
                    publishedFormat,
                    result.published_date
                )

                newsList.add(
                    News(
                        result.id,
                        result.url,
                        result.title,
                        result.byline,
                        date,
                        result.media[0].`media-metadata`[0].url
                    )
                )
            }
            catch (e: Exception) {

                e.printStackTrace()
            }
        }

        return newsList
    }
}