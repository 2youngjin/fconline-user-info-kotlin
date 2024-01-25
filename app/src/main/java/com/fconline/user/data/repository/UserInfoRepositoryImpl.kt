package com.fconline.user.data.repository

import android.util.Log
import com.fconline.user.data.model.Mapper.toDomain
import com.fconline.user.data.model.dto.UserInfoDto
import com.fconline.user.data.source.remote.ApiService
import com.fconline.user.domain.model.UserInfo
import com.fconline.user.domain.repository.UserInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserInfoRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserInfoRepository {
    override fun getUserInfo(ouid: String): Flow<UserInfo> = flow {
        try {
            emit(apiService.getUserInfo(ouid).toDomain())
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