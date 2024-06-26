package com.fconline.user.data.source.remote

import com.fconline.user.data.model.dto.DivisionDto
import com.fconline.user.data.model.dto.MatchIdDto
import com.fconline.user.data.model.dto.MatchTypeDto
import com.fconline.user.data.model.dto.MaxDivisionDto
import com.fconline.user.data.model.dto.UserIdDto
import com.fconline.user.data.model.dto.UserInfoDto
import retrofit2.Response
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

    @GET("/fconline/v1/user/maxdivision") // 역대 최고 등급 조회
    suspend fun getMaxDivision(
        @Query("ouid") ouid: String
    ): List<MaxDivisionDto>

    @GET("/fconline/v1/user/match") // 유저의 매치 기록 조회
    suspend fun getMatch(
        @Query("ouid") ouid: String,
        @Query("matchtype") matchType: Int,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): List<String> // 문자열 리스트 직접 반환

    @GET("/static/fconline/meta/matchtype.json") // 매치 종류 메타데이터 조회
    suspend fun getMatchType(): List<MatchTypeDto>

    @GET("/static/fconline/meta/division.json") // 등급 식별자 메타데이터 조회
    suspend fun getDivision(): List<DivisionDto>
}