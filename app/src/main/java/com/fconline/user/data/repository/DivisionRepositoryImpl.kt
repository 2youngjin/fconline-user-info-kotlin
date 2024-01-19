package com.fconline.user.data.repository

import android.util.Log
import com.fconline.user.data.model.dto.DivisionDto
import com.fconline.user.data.source.remote.ApiService
import com.fconline.user.domain.repository.DivisionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DivisionRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : DivisionRepository {
    override fun getDivision(): Flow<List<DivisionDto>> = flow {
        try {
            emit(apiService.getDivision())
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