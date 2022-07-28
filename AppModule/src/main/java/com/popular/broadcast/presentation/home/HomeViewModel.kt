package com.popular.broadcast.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.popular.broadcast.Config
import com.popular.broadcast.data.session.SessionContext
import com.popular.broadcast.domain.dto.model.News
import com.popular.broadcast.domain.dto.model.NewsRequest
import com.popular.broadcast.domain.usecase.GetNews
import com.popular.broadcast.presentation.base.Event
import com.popular.broadcast.util.ExceptionParser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@Suppress("UNCHECKED_CAST")
class HomeViewModel @Inject constructor(
    private val getNews: GetNews,
    private val sessionContext: SessionContext
) : ViewModel(), NewsListener {

    private val _newsList = MutableLiveData<List<News>>()
    val newsList: LiveData<List<News>> = _newsList

    private val _viewRefreshState = MutableLiveData(false)
    val viewRefreshState: LiveData<Boolean> = _viewRefreshState

    private val _interactorBridge = MutableLiveData<Event<Interactor>>()
    private val interactorBridge: LiveData<Event<Interactor>> = _interactorBridge

    init {

        fetchNews()
    }

    private fun fetchNews() {

        viewModelScope.launch {

            try {

                val section = Config.NEWS_SECTION
                val period = sessionContext.getNewsFetchPeriod()
                val requestParam = NewsRequest(section, period)

                getNews.fetchNews(requestParam).onStart {

                    _viewRefreshState.postValue(true)
                }
                    .catch {

                        _viewRefreshState.postValue(false)
                        postError(ExceptionParser.getMessage(it as Exception))
                    }
                    .collect {

                        if (it is Boolean) {

                            _viewRefreshState.postValue(it)
                        } else {

                            _viewRefreshState.postValue(false)
                            _newsList.value = it as List<News>
                        }
                    }
            } catch (error: Exception) {

                postError(ExceptionParser.getMessage(error))
            }
        }
    }

    fun checkIfDataHasToBeFetched() {

        if (viewRefreshState.value?.not() == true &&
            (newsList.value?.size ?: 0) == 0 &&
            interactorBridge.value?.peekContent() is Interactor.Message
        ) {

            fetchNews()
        }
    }

    fun onRefresh() {

        fetchNews()
    }

    private fun postError(message: Int) {

        _interactorBridge.value = Event(Interactor.Message(message))
    }

    override fun onNewsClicked(news: News) {

        _interactorBridge.value = Event(Interactor.UserClick(news))
    }

    fun getInteractor(): MutableLiveData<Event<Interactor>> {
        return _interactorBridge
    }

    sealed class Interactor {

        class Message(val message: Int) : Interactor()
        class UserClick(val news: News) : Interactor()
    }
}