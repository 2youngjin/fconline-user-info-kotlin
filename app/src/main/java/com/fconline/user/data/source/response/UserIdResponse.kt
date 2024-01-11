package com.fconline.user.data.source.response

import com.google.gson.annotations.SerializedName

data class UserIdResponse(

    @SerializedName("ouid") val id: String // 계정 식별자
)
