package com.fconline.user.domain.usecase

import com.fconline.user.data.model.dto.UserIdDto
import com.fconline.user.domain.repository.UserIdRepository
import javax.inject.Inject

class GetUserIdUseCase @Inject constructor(
    private val userIdRepository: UserIdRepository
) {
    suspend fun getUserId(nickname: String): UserIdDto {
        return userIdRepository.getUserId(nickname)
    }
}