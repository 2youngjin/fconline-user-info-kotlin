package com.fconline.user.domain.repository

import com.fconline.user.data.model.dto.UserIdDto
import kotlinx.coroutines.flow.Flow

interface UserIdRepository {
    fun getUserId(nickName: String): Flow<UserIdDto>
}
