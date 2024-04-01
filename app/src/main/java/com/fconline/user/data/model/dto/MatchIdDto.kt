package com.fconline.user.data.model.dto

import com.google.gson.annotations.SerializedName

data class MatchIdDto(
    @SerializedName("matchId") val matchId: String? = null // 매치 고유 식별자
)