package com.fconline.user.domain.usecase

import com.fconline.user.data.model.UserId
import com.fconline.user.domain.repository.UserIdRepository
import javax.inject.Inject

class GetUserIdUseCase @Inject constructor(private val userIdRepository: UserIdRepository) {
    suspend fun execute(nickname: String): UserId {
        return userIdRepository.getUserIdByNickname(nickname)
    }
}