package com.fconline.user.domain.repository

import com.fconline.user.data.model.UserId
import com.fconline.user.data.remote.UserIdRepositoryRemote
import javax.inject.Inject

class UserIdRepository @Inject constructor(
    private val userRepositoryRemote: UserIdRepositoryRemote
) {
    suspend fun getUserIdByNickname(nickname: String): UserId {
        return userRepositoryRemote.getUserIdByNickname(nickname)
    }
}
