package com.popular.broadcast.data.schedule.model

data class NewsResponse(
    val status: String,
    val copyright: String,
    val num_results: Int,
    val results: List<Result>?
)