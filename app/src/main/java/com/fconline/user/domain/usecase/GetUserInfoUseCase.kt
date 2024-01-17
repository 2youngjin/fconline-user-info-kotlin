package com.fconline.user.domain.usecase

import com.fconline.user.data.model.dto.UserInfoDto
import com.fconline.user.domain.repository.UserInfoRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val userInfoRepository: UserInfoRepository
) {
    suspend fun getUserInfo(ouid: String): UserInfoDto {
        return userInfoRepository.getUserInfo(ouid)
    }
}