package com.popular.broadcast.presentation.home

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.popular.broadcast.data.networking.CoroutineDispatcherProvider
import com.popular.broadcast.domain.schedule.model.NewsRequest
import com.popular.broadcast.domain.schedule.usecase.GetNews
import com.popular.broadcast.util.ExceptionParser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
        private val getNews: GetNews,
        private val coroutineDispatcherProvider: CoroutineDispatcherProvider) : ViewModel() {

    private val _uiState = MutableStateFlow<NewsUiState>(NewsUiState.Empty)
    val uiState: StateFlow<NewsUiState> = _uiState

    fun getNews() {

        _uiState.value = NewsUiState.Loading

        viewModelScope.launch(coroutineDispatcherProvider.IO()) {

            try {

                val section = "all-sections"
                val period = 7
                val requestParam = NewsRequest(section, period)
                val result = getNews.execute(requestParam)

                _uiState.value = NewsUiState.Loaded(HomeItemUiState(section, result))
            }
            catch (error: Exception) {

                _uiState.value = NewsUiState.Error(ExceptionParser.getMessage(error))
            }
        }
    }

    sealed class NewsUiState {

        object Empty : NewsUiState()
        object Loading : NewsUiState()
        class Loaded(val itemState: HomeItemUiState) : NewsUiState()
        class Error(@StringRes val message: Int) : NewsUiState()
    }
}