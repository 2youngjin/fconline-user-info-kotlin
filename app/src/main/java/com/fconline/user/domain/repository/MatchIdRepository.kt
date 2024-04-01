package com.fconline.user.domain.repository

import com.fconline.user.data.model.dto.MatchIdDto
import com.fconline.user.domain.model.MatchId
import kotlinx.coroutines.flow.Flow

interface MatchIdRepository {
    fun getMatchId(ouid: String, matchType: Int, offset: Int, limit: Int): Flow<List<MatchId>>
}