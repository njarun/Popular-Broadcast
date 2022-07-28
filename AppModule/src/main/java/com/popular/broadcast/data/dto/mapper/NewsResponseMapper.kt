package com.popular.broadcast.data.dto.mapper

import com.popular.broadcast.data.dto.model.NewsResponse
import com.popular.broadcast.data.dto.model.Result
import com.popular.broadcast.domain.dto.model.News
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

                val thumbUrl = extractMediaUrl(result, true)
                val banner = extractMediaUrl(result, false)

                newsList.add(
                    News(
                        result.id,
                        result.url,
                        result.title,
                        result.abstract,
                        result.byline,
                        result.section,
                        date,
                        thumbUrl,
                        banner
                    )
                )

                newsList.sortByDescending { it.updated }
            } catch (e: Exception) {

                e.printStackTrace()
            }
        }

        return newsList
    }

    private fun extractMediaUrl(result: Result, isThumb: Boolean) =
        if (result.media.isNotEmpty())
            if (result.media[if (isThumb) 0 else result.media.size - 1].`media-metadata`.isNotEmpty())
                result.media[if (isThumb) 0 else result.media.size - 1].`media-metadata`[if (isThumb) 0 else result.media[result.media.size - 1].`media-metadata`.size - 1].url
            else null
        else null
}