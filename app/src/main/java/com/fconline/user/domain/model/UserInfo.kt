package com.fconline.user.domain.model

data class UserInfo(
    val id: String, // 유저 고유 식별자
    val nickName: String, // 구단주 명
    val level: String // 구단주 레벨
)
