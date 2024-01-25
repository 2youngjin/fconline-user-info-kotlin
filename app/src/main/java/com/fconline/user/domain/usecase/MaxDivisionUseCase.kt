package com.fconline.user.domain.usecase

import com.fconline.user.domain.repository.MaxDivisionRepository
import javax.inject.Inject

class MaxDivisionUseCase @Inject constructor(
    private val maxDivisionRepository: MaxDivisionRepository
) {
    fun getMaxDivision(ouid: String) = maxDivisionRepository.getMaxDivision(ouid)
}