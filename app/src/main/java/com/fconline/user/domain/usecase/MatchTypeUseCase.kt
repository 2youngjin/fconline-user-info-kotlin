package com.fconline.user.domain.usecase

import com.fconline.user.domain.repository.MatchTypeRepository
import javax.inject.Inject

class MatchTypeUseCase @Inject constructor(
    private val matchTypeRepository: MatchTypeRepository
) {
    fun getMatchType() = matchTypeRepository.getMatchType()
}