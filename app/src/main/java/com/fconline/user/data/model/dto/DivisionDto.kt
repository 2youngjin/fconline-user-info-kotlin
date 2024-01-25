package com.fconline.user.data.model.dto

import com.google.gson.annotations.SerializedName

data class DivisionDto(
    @SerializedName("divisionId") val divisionId: Int? = null, // 등급 식별자
    @SerializedName("divisionName") val divisionName: String? = null // 등급 식별자명
)
