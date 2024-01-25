package com.fconline.user.data.repository

import android.util.Log
import com.fconline.user.data.model.Mapper.toDomain
import com.fconline.user.data.model.dto.MatchTypeDto
import com.fconline.user.data.source.remote.ApiService
import com.fconline.user.domain.model.MatchType
import com.fconline.user.domain.repository.MatchTypeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MatchTypeRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MatchTypeRepository {
    override fun getMatchType(): Flow<List<MatchType>> = flow {
        try {
            emit(apiService.getMatchType().map { it.toDomain() })
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