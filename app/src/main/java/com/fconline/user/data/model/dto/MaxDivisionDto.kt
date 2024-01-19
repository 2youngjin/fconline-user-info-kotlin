package com.fconline.user.data.model.dto

import com.google.gson.annotations.SerializedName

data class MaxDivisionDto(
    @SerializedName("matchType") val matchType: Int, // 매치 종류(/metadata/matchtype API 참고)
    @SerializedName("division") val division: Int, // 등급 식별자 (공식경기 /metadata/division API, 볼타모드 /metadata/division_volta API 참고)
    @SerializedName("achievementDate") val achievementDate: String // 최고 등급 달성 일자 (UTC0)
)
