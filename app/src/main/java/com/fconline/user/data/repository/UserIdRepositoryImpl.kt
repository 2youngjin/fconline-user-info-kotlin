package com.fconline.user.data.repository

import com.fconline.user.domain.UserIdRepository
import com.fconline.user.data.source.OpenApiService
import javax.inject.Inject

class UserIdRepositoryImpl @Inject constructor(
    private val openApiService: OpenApiService
) : UserIdRepository {
    override suspend fun getUserId(nickName: String): String {
        return openApiService.getUserId(nickName).toString()
    }
}
