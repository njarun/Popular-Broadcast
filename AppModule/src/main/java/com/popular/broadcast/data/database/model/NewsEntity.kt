package com.popular.broadcast.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
class NewsEntity(
    @PrimaryKey
    val id: Long,
    val url: String,
    val title: String,
    val summary: String,
    val byline: String,
    val section: String,
    val updated: Long,
    val thumb: String?,
    val banner: String?
)