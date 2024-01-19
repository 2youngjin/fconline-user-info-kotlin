package com.fconline.user.data.model

import com.fconline.user.data.model.dto.DivisionDto
import com.fconline.user.data.model.dto.MatchTypeDto
import com.fconline.user.data.model.dto.MaxDivisionDto
import com.fconline.user.data.model.dto.UserIdDto
import com.fconline.user.data.model.dto.UserInfoDto
import com.fconline.user.domain.model.Division
import com.fconline.user.domain.model.MatchType
import com.fconline.user.domain.model.MaxDivision
import com.fconline.user.domain.model.UserId
import com.fconline.user.domain.model.UserInfo

object Mapper {

    fun DivisionDto.toDomain() = Division(
        divisionId = this.divisionId ?: -1,
        divisionName = this.divisionName ?: ""
    )

    fun MatchTypeDto.toDomain() = MatchType(
        matchType = this.matchType ?: -1,
        desc = this.desc ?: ""
    )

    fun MaxDivisionDto.toDomain() = MaxDivision(
        matchType = this.matchType ?: -1,
        division = this.division ?: -1,
        achievementDate = this.achievementDate ?: ""
    )

    fun UserIdDto.toDomain() = UserId(
        id = this.id ?: ""
    )

    fun UserInfoDto.toDomain() = UserInfo(
        id = this.id ?: "",
        nickName = this.nickName ?: "",
        level = this.level ?: ""
    )

}
