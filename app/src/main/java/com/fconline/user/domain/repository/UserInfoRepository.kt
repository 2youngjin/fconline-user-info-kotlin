package com.fconline.user.domain.repository

import com.fconline.user.data.model.dto.UserInfoDto

interface UserInfoRepository {
    suspend fun getUserInfo(ouid: String): UserInfoDto
}