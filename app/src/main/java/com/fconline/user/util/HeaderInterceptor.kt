package com.fconline.user.util

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(private val authorization: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("x-nxopen-api-key", authorization)
            .build()
        return chain.proceed(request)
    }
}