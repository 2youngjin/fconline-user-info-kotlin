package com.fconline.user.domain.usecase

import com.fconline.user.domain.repository.MatchIdRepository
import javax.inject.Inject

class MatchIdUseCase @Inject constructor(
    private val matchIdRepository: MatchIdRepository
) {
    fun getMatchId(ouid: String, matchType: Int, offSet: Int, limit: Int) =
        matchIdRepository.getMatchId(ouid, matchType, offSet, limit)
}