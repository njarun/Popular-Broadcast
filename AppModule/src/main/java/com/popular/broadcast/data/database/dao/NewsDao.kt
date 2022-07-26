package com.popular.broadcast.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.popular.broadcast.data.database.model.NewsEntity

@Dao
interface NewsDao {

    @Query("SELECT * FROM news WHERE updated >= :to ORDER BY updated DESC")
    fun getNews(to: Long): List<NewsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news: List<NewsEntity>)

    @Query("DELETE FROM news")
    fun delete()
}