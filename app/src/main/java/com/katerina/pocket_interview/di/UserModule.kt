package com.katerina.pocket_interview.di

import com.katerina.pocket_interview.core.data.repositories.UserRepositoryImpl
import com.katerina.pocket_interview.core.domain.repositories.UserRepository
import dagger.Module
import dagger.Provides


@Module
class UserModule {

    @Provides
    fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl)
            : UserRepository = userRepositoryImpl
}