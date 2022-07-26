package com.popular.broadcast.presentation.base.state

import androidx.annotation.StringRes

sealed class UiState {

    object Empty : UiState()
    object Loading : UiState()
    class Loaded(val itemState: Any) : UiState()
    class Error(@StringRes val message: Int) : UiState()
}