package com.fconline.user.data.repository

import com.fconline.user.data.model.UserId
import com.fconline.user.data.source.remote.ApiService
import com.fconline.user.domain.repository.UserIdRepository
import javax.inject.Inject

class UserIdRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserIdRepository {
    override suspend fun getUserId(nickName: String): UserId {
        return apiService.getUserId(nickName)
    }
}