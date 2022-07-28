package com.popular.broadcast

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.popular.broadcast.data.database.AppDatabase
import com.popular.broadcast.data.database.dao.NewsDao
import com.popular.broadcast.data.database.model.NewsEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class NewsDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var newsDatabase: AppDatabase
    private lateinit var newsDao: NewsDao

    @Before
    fun setup() {

        val context = ApplicationProvider.getApplicationContext<Context>()
        newsDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        newsDao = newsDatabase.newsDao()
    }

    @After
    fun teardown() {
        newsDatabase.close()
    }

    @Test
    fun insertNews() = runBlockingTest {

        val newsEntity = createNewsObject(200)
        newsDao.insert(newsEntity)
        val newsList = newsDao.getNews(200)

        var operationSuccess = false
        for (item in newsList) {
            if (item.id == newsEntity.id) {
                operationSuccess = true
                break
            }
        }

        Assert.assertEquals(true, operationSuccess)

        //assertThat(dbNewsEntity).contains(newsEntity)
    }

    @Test
    fun deleteNews() = runBlockingTest {

        val newsEntity = createNewsObject(300)
        newsDao.insert(newsEntity)
        newsDao.delete(newsEntity.id)
        val newsList = newsDao.getNews(300)

        var operationSuccess = true
        for (item in newsList) {
            if (item.id == newsEntity.id) {
                operationSuccess = false
                break
            }
        }

        Assert.assertEquals(true, operationSuccess)
    }

    @Test
    fun updateNews() = runBlockingTest {

        val newsEntity = createNewsObject(400)
        newsDao.insert(newsEntity)
        val latestNews = newsEntity.copy(id = 401)
        newsDao.update(latestNews)
        val newsList = newsDao.getNews(400)

        var operationSuccess = false
        for (item in newsList) {
            if (item.id == newsEntity.id) {
                operationSuccess = true
                break
            }
        }

        Assert.assertEquals(true, operationSuccess)
    }

    private fun createNewsList(startId: Long): List<NewsEntity> {

        val newsList: ArrayList<NewsEntity> = ArrayList()

        for (id in startId - 10..startId) {
            newsList.add(createNewsObject(id))
        }

        return newsList
    }

    private fun createNewsObject(id: Long): NewsEntity {
        return NewsEntity(id, "URL", "Title", "Summary", "ByLine", "Section", id, "thumb", "banner")
    }
}