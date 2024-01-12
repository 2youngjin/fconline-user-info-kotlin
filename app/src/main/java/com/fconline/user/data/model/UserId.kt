package com.fconline.user.data.model

import com.google.gson.annotations.SerializedName

data class UserId(
    @SerializedName("ouid") val id: String // 계정 식별자
)
