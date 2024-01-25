package com.fconline.user.domain.usecase

import com.fconline.user.data.model.dto.UserInfoDto
import com.fconline.user.domain.repository.UserInfoRepository
import javax.inject.Inject

class UserInfoUseCase @Inject constructor(
    private val userInfoRepository: UserInfoRepository
) {
    fun getUserInfo(ouid: String) = userInfoRepository.getUserInfo(ouid)
}