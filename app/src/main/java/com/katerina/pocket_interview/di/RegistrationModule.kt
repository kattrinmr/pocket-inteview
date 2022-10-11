package com.katerina.pocket_interview.di

import com.katerina.pocket_interview.auth.data.repositories.RegistrationRepositoryImpl
import com.katerina.pocket_interview.auth.domain.repositories.RegistrationRepository
import dagger.Module
import dagger.Provides

@Module
class RegistrationModule {

    @Provides
    fun provideRegistrationRepository(registrationRepositoryImpl: RegistrationRepositoryImpl)
        : RegistrationRepository = registrationRepositoryImpl
}