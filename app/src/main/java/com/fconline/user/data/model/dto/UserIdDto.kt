package com.fconline.user.data.model.dto

import com.google.gson.annotations.SerializedName

data class UserIdDto(
    @SerializedName("ouid") val id: String? = null // 계정 식별자
)
