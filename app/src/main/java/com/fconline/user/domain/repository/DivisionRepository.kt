package com.fconline.user.domain.repository

import com.fconline.user.domain.model.Division
import kotlinx.coroutines.flow.Flow

interface DivisionRepository {
    fun getDivision(): Flow<List<Division>>
}