package com.popular.broadcast.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
class NewsEntity(
    @PrimaryKey
    val id: Long,
    val url: String,
    val title: String,
    val byline: String,
    val updated: Long,
    val thumb: String?,
)