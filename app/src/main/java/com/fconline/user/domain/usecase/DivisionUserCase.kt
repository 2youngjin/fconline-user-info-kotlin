package com.fconline.user.domain.usecase

import com.fconline.user.domain.repository.DivisionRepository
import javax.inject.Inject

class DivisionUserCase @Inject constructor(
    private val divisionRepository: DivisionRepository
) {
    fun getDivision() = divisionRepository.getDivision()
}