package com.popular.broadcast.data.router.factory

import com.popular.broadcast.data.router.source.NewsEntityData
import com.popular.broadcast.data.router.source.local.LocalNewsEntityData
import com.popular.broadcast.data.router.source.network.NetworkNewsEntityData
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