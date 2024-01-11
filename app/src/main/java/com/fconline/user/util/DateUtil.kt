package com.fconline.user.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

object DateUtil {
    @SuppressLint("SimpleDateFormat")
    val serverDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
}