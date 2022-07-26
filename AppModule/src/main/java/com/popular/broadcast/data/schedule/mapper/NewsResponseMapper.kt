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

                val imageUrl = if (result.media.isNotEmpty())
                    if (result.media[0].`media-metadata`.isNotEmpty())
                        result.media[0].`media-metadata`[0].url
                    else null
                else null

                newsList.add(
                    News(result.id,
                        result.url,
                        result.title,
                        result.abstract,
                        result.byline,
                        result.section,
                        date,
                        imageUrl
                    )
                )

                newsList.sortByDescending { it.updated }
            }
            catch (e: Exception) {

                e.printStackTrace()
            }
        }

        return newsList
    }
}