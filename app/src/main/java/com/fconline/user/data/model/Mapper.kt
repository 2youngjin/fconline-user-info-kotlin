package com.fconline.user.data.model

import com.fconline.user.data.model.dto.UserInfoDto
import com.fconline.user.domain.model.UserInfo

object Mapper {

    fun UserInfoDto.toUserInfo() = UserInfo(
        id = id,
        nickName = nickName,
        level = level
    )
}