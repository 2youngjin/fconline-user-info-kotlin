package com.fconline.user.domain.repository

import com.fconline.user.domain.model.MatchType
import kotlinx.coroutines.flow.Flow

interface MatchTypeRepository {
    fun getMatchType(): Flow<List<MatchType>>
}