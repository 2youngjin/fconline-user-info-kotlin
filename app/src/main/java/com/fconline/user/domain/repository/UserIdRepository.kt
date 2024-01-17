package com.fconline.user.domain.repository

import com.fconline.user.data.model.UserId

interface UserIdRepository {
    suspend fun getUserId(nickName: String): UserId
}
