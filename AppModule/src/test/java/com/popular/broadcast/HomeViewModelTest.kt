package com.popular.broadcast

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.popular.broadcast.data.networking.CoroutineDispatcherProvider
import com.popular.broadcast.data.networking.repository.NewsRepositoryImpl
import com.popular.broadcast.data.session.SessionContext
import com.popular.broadcast.domain.dto.model.News
import com.popular.broadcast.domain.dto.model.NewsRequest
import com.popular.broadcast.domain.usecase.GetNews
import com.popular.broadcast.presentation.home.HomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val sessionContext = mock<SessionContext>()
    private var newsRepository = mock<NewsRepositoryImpl>()

    private lateinit var getNews: GetNews
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {

        getNews = GetNews(newsRepository, CoroutineDispatcherProvider())
    }

    @Test
    fun `initial state of view model`() = runBlocking {

        whenever(sessionContext.getNewsFetchPeriod())
            .thenReturn(18)

        viewModel = HomeViewModel(getNews, sessionContext)

        Assert.assertEquals(false, viewModel.viewRefreshState.value)
    }

    @Test
    fun `loading state of view model`() = runBlocking {

        val req = NewsRequest(Config.NEWS_SECTION, 100)

        val newsList = createNewsList(110)

        whenever(sessionContext.getNewsFetchPeriod())
            .thenReturn(18)

        whenever(newsRepository.getNewsFromLocal(req))
            .thenReturn(newsList)

        whenever(newsRepository.getNewsFromNetwork(req))
            .thenReturn(newsList)

        viewModel = HomeViewModel(getNews, sessionContext)

        TimeUnit.SECONDS.sleep(5)

        Assert.assertEquals(null, viewModel.newsList.value)
    }

    private fun createNewsList(startId: Long): List<News> {

        val newsList: ArrayList<News> = ArrayList()

        for (id in startId - 10..startId) {
            newsList.add(createNewsObject(id))
        }

        return newsList
    }

    private fun createNewsObject(id: Long): News {
        return News(id, "URL", "Title", "Summary", "ByLine", "Section", id, "thumb", "banner")
    }
}