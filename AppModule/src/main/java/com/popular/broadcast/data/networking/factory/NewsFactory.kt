package com.popular.broadcast.data.networking.factory

import com.popular.broadcast.data.networking.source.NewsEntityData
import com.popular.broadcast.data.networking.source.local.LocalNewsEntityData
import com.popular.broadcast.data.networking.source.network.NetworkNewsEntityData
import com.popular.broadcast.util.Source
import javax.inject.Inject

class NewsFactory @Inject constructor(
    private val networkNewsEntityData: NetworkNewsEntityData,
    private val localNewsEntityData: LocalNewsEntityData
) {

    fun create(source: Source): NewsEntityData {

        return when (source) {

            Source.NETWORK -> networkNewsEntityData
            else -> localNewsEntityData
        }
    }
}