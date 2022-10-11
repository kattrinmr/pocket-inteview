package com.katerina.pocket_interview.di

import com.google.firebase.auth.FirebaseAuth
import com.katerina.pocket_interview.auth.data.repositories.AuthRepositoryImpl
import com.katerina.pocket_interview.auth.domain.repositories.AuthRepository
import dagger.Module
import dagger.Provides

@Module
class AuthModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    fun provideAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository = authRepositoryImpl
}