package com.fconline.user.data.repository

import android.util.Log
import com.fconline.user.data.model.Mapper.toDomain
import com.fconline.user.data.model.dto.UserIdDto
import com.fconline.user.data.source.remote.ApiService
import com.fconline.user.domain.model.UserId
import com.fconline.user.domain.repository.UserIdRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserIdRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserIdRepository {
    override fun getUserId(nickName: String): Flow<UserId> = flow {
        try {
            emit(apiService.getUserId(nickName).toDomain())
        } catch (e: Exception) {
            // Handle errors as needed
            throw e
        }
    }.catch { e ->
        // Handle errors emitted by the flow
        Log.e("API_ERROR", "Flow Error: ${e.message}")
        throw e
    }
}