package com.popular.broadcast.data.session

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Jose on 3/24/16.
 */

@Module
@InstallIn(SingletonComponent::class)
class SessionContext {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private var newsFetchPeriod: Int? = null

    @Singleton
    @Provides
    fun providerSessionContext(@ApplicationContext context: Context) : SessionContext {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        editor = sharedPreferences.edit()
        return this
    }

    fun getNewsFetchPeriod(): Int {

        if(newsFetchPeriod == null)
            newsFetchPeriod = sharedPreferences.getInt("newsFetchPeriod", 7)

        return newsFetchPeriod as Int
    }

    fun setNewsFetchPeriod(newsFetchPeriod: Int) {

        editor.putInt("newsFetchPeriod", newsFetchPeriod)
        editor.apply()

        this.newsFetchPeriod = newsFetchPeriod
    }
}