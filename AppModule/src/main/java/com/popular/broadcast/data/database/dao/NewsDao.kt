package com.popular.broadcast.data.database.dao

import androidx.room.*
import com.popular.broadcast.data.database.model.NewsEntity

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news: NewsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news: List<NewsEntity>)

    @Query("SELECT COUNT(id) FROM news")
    fun getCount(): Int

    @Query("SELECT * FROM news WHERE updated >= :to ORDER BY updated DESC")
    fun getNews(to: Long): List<NewsEntity>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(news: NewsEntity)

    @Query("DELETE FROM news WHERE id = :id")
    fun delete(id: Long)

    @Query("DELETE FROM news")
    fun delete()
}