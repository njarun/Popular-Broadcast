package com.popular.broadcast.domain.schedule.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class News(val id: Long,
    val url: String,
    val title: String,
    val summary: String,
    val byline: String,
    val section: String,
    val updated: Long,
    val thumb: String?,
) : Parcelable