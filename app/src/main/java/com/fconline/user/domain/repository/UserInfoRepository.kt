package com.fconline.user.domain.repository

import com.fconline.user.domain.model.UserInfo
import kotlinx.coroutines.flow.Flow

interface UserInfoRepository {
    fun getUserInfo(ouid: String): Flow<UserInfo>
}