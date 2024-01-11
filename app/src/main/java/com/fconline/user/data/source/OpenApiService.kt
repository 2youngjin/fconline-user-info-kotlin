package com.fconline.user.data.source

import com.fconline.user.data.source.response.UserIdResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenApiService {

    @GET("/fconline/v1/id") // 계정 식별자 조회
    suspend fun getUserId(
        @Query("nickname") nickname: String
    ): Response<UserIdResponse>
}