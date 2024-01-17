package com.fconline.user.data.source.remote

import com.fconline.user.data.model.dto.UserIdDto
import com.fconline.user.data.model.dto.UserInfoDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/fconline/v1/id") // 계정 식별자 조회
    suspend fun getUserId(
        @Query("nickname") nickname: String
    ): UserIdDto

    @GET("/fconline/v1/user/basic") // 기본 정보 조회
    suspend fun getUserInfo(
        @Query("ouid") ouid: String
    ): UserInfoDto
}