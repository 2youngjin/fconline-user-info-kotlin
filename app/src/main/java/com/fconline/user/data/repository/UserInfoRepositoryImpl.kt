package com.fconline.user.data.repository

import com.fconline.user.data.model.dto.UserInfoDto
import com.fconline.user.data.source.remote.ApiService
import com.fconline.user.domain.repository.UserInfoRepository
import javax.inject.Inject

class UserInfoRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserInfoRepository {
    override suspend fun getUserInfo(ouid: String): UserInfoDto {
        return apiService.getUserInfo(ouid)
    }
}