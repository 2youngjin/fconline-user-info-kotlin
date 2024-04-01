package com.fconline.user.data.repository

import android.util.Log
import com.fconline.user.data.model.Mapper.toDomain
import com.fconline.user.data.source.remote.ApiService
import com.fconline.user.domain.model.MatchId
import com.fconline.user.domain.repository.MatchIdRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MatchIdRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MatchIdRepository {
    override fun getMatchId(
        ouid: String,
        matchType: Int,
        offset: Int,
        limit: Int
    ): Flow<List<MatchId>> = flow {
        try {
            emit(apiService.getMatch(ouid, matchType, offset, limit).map { MatchId(it) })
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