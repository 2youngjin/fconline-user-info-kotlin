package com.fconline.user.data.repository

import android.util.Log
import com.fconline.user.data.model.Mapper.toDomain
import com.fconline.user.data.source.remote.ApiService
import com.fconline.user.domain.model.MaxDivision
import com.fconline.user.domain.repository.MaxDivisionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MaxDivisionRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MaxDivisionRepository {
    override fun getMaxDivision(ouid: String): Flow<List<MaxDivision>> = flow {
        try {
            emit(apiService.getMaxDivision(ouid).map { it.toDomain() })
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
