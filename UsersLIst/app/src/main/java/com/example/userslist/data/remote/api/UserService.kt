package com.example.userslist.data.remote.api

import com.example.userslist.data.remote.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("users/{name}/repos")
    suspend fun getUserData(@Path("name") name: String): List<UserDto>
}