package com.fconline.user.domain.usecase

import com.fconline.user.data.model.dto.UserIdDto
import com.fconline.user.domain.repository.UserIdRepository
import javax.inject.Inject

class UserIdUseCase @Inject constructor(
    private val userIdRepository: UserIdRepository
) {
    fun getUserId(nickname: String) = userIdRepository.getUserId(nickname)
}