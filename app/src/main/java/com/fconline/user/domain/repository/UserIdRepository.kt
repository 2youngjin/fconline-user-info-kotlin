package com.fconline.user.domain.repository

import com.fconline.user.domain.model.UserId
import kotlinx.coroutines.flow.Flow

interface UserIdRepository {
    fun getUserId(nickName: String): Flow<UserId>
}
