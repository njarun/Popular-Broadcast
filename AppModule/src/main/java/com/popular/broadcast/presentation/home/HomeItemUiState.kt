package com.popular.broadcast.presentation.home

import com.popular.broadcast.domain.schedule.model.News

data class HomeItemUiState(
    val section: String,
    val newsList: List<News>
)