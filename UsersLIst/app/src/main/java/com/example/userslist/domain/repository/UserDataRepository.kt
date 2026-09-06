package com.example.userslist.domain.repository

import com.example.userslist.domain.model.User

interface UserDataRepository {
    suspend fun getUserData(username: String): List<User>
}