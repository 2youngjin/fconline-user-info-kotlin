package com.fconline.user.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
object DateUtil {
    val yyyy_MM_dd = SimpleDateFormat("yyyy.MM.dd")
    val dbDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val serverDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

    fun convertPrintDateString(date: Date? = Date()) = yyyy_MM_dd.format(date)
}