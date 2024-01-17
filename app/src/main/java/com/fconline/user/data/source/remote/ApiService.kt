package com.fconline.user.data.source.remote

import com.fconline.user.data.model.UserId
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @GET("/fconline/v1/id") // 계정 식별자 조회
    suspend fun getUserId(
        @Query("nickname") nickname: String
    ): UserId
}