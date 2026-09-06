package com.example.userslist.domain.usecase

import com.example.userslist.domain.model.User
import com.example.userslist.domain.repository.UserDataRepository
import javax.inject.Inject

class GetUserDataUseCase @Inject constructor(
    private val repository: UserDataRepository
) {
    suspend operator fun invoke(username: String): List<User> = repository.getUserData(username)
}