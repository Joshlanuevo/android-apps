package com.example.userslist.modules

import com.example.userslist.data.repository.UserDataRepositoryImpl
import com.example.userslist.domain.repository.UserDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserDataRepository(
        impl: UserDataRepositoryImpl
    ): UserDataRepository
}