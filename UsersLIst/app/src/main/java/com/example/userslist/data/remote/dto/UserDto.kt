package com.example.userslist.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val name: String,
    @SerialName("full_name") val fullName: String,
    val owner: OwnerDto
)

@Serializable
data class OwnerDto(
    @SerialName("avatar_url") val avatarUrl: String
)