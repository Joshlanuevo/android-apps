package com.example.userslist.data.mapper

import com.example.userslist.data.remote.dto.UserDto
import com.example.userslist.domain.model.User

fun UserDto.toDomain(): User =
    User(
        name = name,
        fullName = fullName,
        avatarUrl = owner.avatarUrl,
    )