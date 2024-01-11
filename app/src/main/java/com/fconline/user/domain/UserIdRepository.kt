package com.fconline.user.domain

interface UserIdRepository {
    suspend fun getUserId(nickName: String): String
}
