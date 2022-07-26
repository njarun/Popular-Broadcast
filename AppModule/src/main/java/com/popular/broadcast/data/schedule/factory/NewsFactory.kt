package com.popular.broadcast.data.schedule.factory

import com.popular.broadcast.data.schedule.source.NewsEntityData
import com.popular.broadcast.data.schedule.source.local.LocalNewsEntityData
import com.popular.broadcast.data.schedule.source.network.NetworkNewsEntityData
import com.popular.broadcast.util.Source
import javax.inject.Inject

class NewsFactory @Inject constructor(private val networkNewsEntityData: NetworkNewsEntityData,
    private val localNewsEntityData: LocalNewsEntityData) {

    fun create(source: Source): NewsEntityData {

        return when (source) {

            Source.NETWORK -> networkNewsEntityData
            else -> localNewsEntityData
        }
    }
}