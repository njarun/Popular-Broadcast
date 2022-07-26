package com.popular.broadcast.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object TimeUtil {

    fun setTime(dateString: String, time: String): Long {
        return getTimeMilis(dateString, time)
    }

    fun Long.getTimeFormated(): String =
        SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date(this))

    fun getDateFormatted(date: Date): String =
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date)

    @JvmStatic
    fun getDateFormatted(date: Long): String =
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date(date))

    @SuppressLint("SimpleDateFormat")
    fun getTimestamp(dateString: String, plusDay: Int): Long {
        val c = Calendar.getInstance()
        c.time = SimpleDateFormat("yyyy-MM-dd").parse(dateString) ?: Date(0)
        c.add(Calendar.DATE, plusDay)
        return c.time.time
    }

    @SuppressLint("SimpleDateFormat")
    fun getTimestamp(dateString: String): Long {
        val date = SimpleDateFormat("yyyy-MM-dd").parse(dateString) ?: Date(0)
        return date.time
    }

    fun getTimestamp(format: String, dateString: String?): Long {
        val date = SimpleDateFormat(format).parse(dateString) ?: Date(0)
        return date.time
    }

    fun getTimestamp(format1: String, dateString1: String?, format2: String, dateString2: String?): Long {

        val date = if(!dateString1.isNullOrEmpty()) {
            SimpleDateFormat(format1).parse(dateString1) ?: Date()
        }
        else if(!dateString2.isNullOrEmpty()) {
            SimpleDateFormat(format2).parse(dateString2) ?: Date()
        }
        else {
            Date()
        }

        return date.time
    }

    @SuppressLint("SimpleDateFormat")
    private fun getTimeMilis(dayTimestamp: String, time: String): Long {
        val dateTime = dayTimestamp.plus(" ").plus(time)
        return SimpleDateFormat("yyyy-MM-dd hh:mm").parse(dateTime)?.time ?: 0
    }
}