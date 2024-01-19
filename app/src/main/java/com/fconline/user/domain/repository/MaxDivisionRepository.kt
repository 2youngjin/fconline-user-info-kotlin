package com.fconline.user.domain.repository

import com.fconline.user.data.model.dto.MaxDivisionDto
import kotlinx.coroutines.flow.Flow

interface MaxDivisionRepository {
    fun getMaxDivision(ouid: String): Flow<List<MaxDivisionDto>>
}