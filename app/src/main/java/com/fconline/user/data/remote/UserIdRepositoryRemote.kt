package com.fconline.user.data.remote

import com.fconline.user.data.model.UserId
import javax.inject.Inject

class UserIdRepositoryRemote @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getUserIdByNickname(nickname: String): UserId {
        return apiService.getUserId(nickname)
    }
}