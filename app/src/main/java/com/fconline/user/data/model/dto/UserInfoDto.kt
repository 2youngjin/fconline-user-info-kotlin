package com.fconline.user.data.model.dto

import com.google.gson.annotations.SerializedName

data class UserInfoDto(
    @SerializedName("ouid") val id: String? = null, // 유저 고유 식별자
    @SerializedName("nickname") val nickName: String? = null, // 구단주 명
    @SerializedName("level") val level: String? = null // 구단주 레벨
)
