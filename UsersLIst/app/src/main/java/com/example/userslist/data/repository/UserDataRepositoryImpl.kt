package com.example.userslist.data.repository

import com.example.userslist.data.mapper.toDomain
import com.example.userslist.data.remote.api.UserService
import com.example.userslist.domain.model.User
import com.example.userslist.domain.repository.UserDataRepository
import javax.inject.Inject

class UserDataRepositoryImpl @Inject constructor(
    private val api: UserService,
) : UserDataRepository {
    override suspend fun getUserData(username: String): List<User> {
        return api.getUserData(username).map { it.toDomain() }
    }
}