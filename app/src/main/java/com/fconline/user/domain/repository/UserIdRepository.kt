package com.fconline.user.domain.repository

import com.fconline.user.data.model.dto.UserIdDto

interface UserIdRepository {
    suspend fun getUserId(nickName: String): UserIdDto
}
