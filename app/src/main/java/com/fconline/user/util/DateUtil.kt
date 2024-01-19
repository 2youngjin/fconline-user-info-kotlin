package com.fconline.user.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

object DateUtil {
    @SuppressLint("SimpleDateFormat")
    val serverDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    val yyyy_MM_dd = SimpleDateFormat("yyyy.MM.dd")

    fun convertPrintDateString(date: Date? = Date()) = yyyy_MM_dd.format(date)
}