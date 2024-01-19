package com.fconline.user.domain.repository

import com.fconline.user.data.model.dto.MatchTypeDto
import kotlinx.coroutines.flow.Flow

interface MatchTypeRepository {
    fun getMatchType(): Flow<List<MatchTypeDto>>
}