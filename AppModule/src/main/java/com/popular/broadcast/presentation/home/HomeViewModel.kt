package com.popular.broadcast.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.popular.broadcast.data.networking.CoroutineDispatcherProvider
import com.popular.broadcast.domain.schedule.model.News
import com.popular.broadcast.domain.schedule.model.NewsRequest
import com.popular.broadcast.domain.schedule.usecase.GetNews
import com.popular.broadcast.presentation.base.state.UiState
import com.popular.broadcast.presentation.home.state.HomeItemUiState
import com.popular.broadcast.util.ExceptionParser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
@HiltViewModel
class HomeViewModel @Inject constructor(
        private val getNews: GetNews,
        private val coroutineDispatcherProvider: CoroutineDispatcherProvider) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Empty)
    val uiState: StateFlow<UiState> = _uiState

    fun getNews() {

        _uiState.value = UiState.Loading

        viewModelScope.launch(coroutineDispatcherProvider.IO()) {

            try {

                val section = "all-sections"
                val period = 7
                val requestParam = NewsRequest(section, period)

                getNews.collectNews(requestParam).collect {

                    _uiState.value = if(it is UiState) {
                        it
                    }
                    else {

                        UiState.Loaded(HomeItemUiState(section, it as List<News>))
                    }
                }

                /*val result = getNews.execute(requestParam)
                _uiState.value = UiState.Loaded(HomeItemUiState(section, result))*/
            }
            catch (error: Exception) {

                _uiState.value = UiState.Error(ExceptionParser.getMessage(error))
            }
        }
    }
}