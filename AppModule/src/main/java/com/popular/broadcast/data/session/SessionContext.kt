package com.popular.broadcast.data.session

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import javax.inject.Inject

class SessionContext @Inject constructor(context: Context) {

    private val sharedPreferences: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    private var newsFetchPeriod: Int? = null

    fun getNewsFetchPeriod(): Int {

        if (newsFetchPeriod == null)
            newsFetchPeriod = sharedPreferences.getInt("newsFetchPeriod", 7)

        return newsFetchPeriod as Int
    }

    fun setNewsFetchPeriod(newsFetchPeriod: Int) {

        editor.putInt("newsFetchPeriod", newsFetchPeriod)
        editor.apply()

        this.newsFetchPeriod = newsFetchPeriod
    }
}