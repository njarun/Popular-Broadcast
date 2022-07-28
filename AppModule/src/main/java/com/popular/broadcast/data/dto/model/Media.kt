package com.popular.broadcast.data.dto.model

data class Media(
    val approved_for_syndication: Int,
    val caption: String,
    val copyright: String,
    val `media-metadata`: List<MediaMetadata>,
    val subtype: String,
    val type: String
)