package com.fconline.user.domain.repository

import com.fconline.user.data.model.dto.DivisionDto
import kotlinx.coroutines.flow.Flow

interface DivisionRepository {
    fun getDivision(): Flow<List<DivisionDto>>
}