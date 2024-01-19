package com.fconline.user.domain.repository

import com.fconline.user.data.model.dto.UserInfoDto
import kotlinx.coroutines.flow.Flow

interface UserInfoRepository {
    fun getUserInfo(ouid: String): Flow<UserInfoDto>
}