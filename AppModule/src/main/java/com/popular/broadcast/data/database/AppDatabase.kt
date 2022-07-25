package com.popular.broadcast.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.popular.broadcast.data.database.dao.NewsDao
import com.popular.broadcast.data.database.model.NewsEntity

@Database(
    version = 1,
    entities = [NewsEntity::class],
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao
}