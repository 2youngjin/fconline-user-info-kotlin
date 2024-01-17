package com.fconline.user.data.repository

import com.fconline.user.data.model.dto.UserIdDto
import com.fconline.user.data.source.remote.ApiService
import com.fconline.user.domain.repository.UserIdRepository
import javax.inject.Inject

class UserIdRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserIdRepository {
    override suspend fun getUserId(nickName: String): UserIdDto {
        return apiService.getUserId(nickName)
    }
}