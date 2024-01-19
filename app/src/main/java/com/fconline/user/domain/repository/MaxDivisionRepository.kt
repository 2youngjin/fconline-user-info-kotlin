package com.fconline.user.domain.repository

import com.fconline.user.domain.model.MaxDivision
import kotlinx.coroutines.flow.Flow

interface MaxDivisionRepository {
    fun getMaxDivision(ouid: String): Flow<List<MaxDivision>>
}