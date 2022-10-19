package com.katerina.pocket_interview.di

import com.katerina.pocket_interview.home.data.repositories.HomeRepositoryImpl
import com.katerina.pocket_interview.home.domain.repositories.HomeRepository
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @Provides
    fun provideHomeRepository(homeRepositoryImpl: HomeRepositoryImpl)
            : HomeRepository = homeRepositoryImpl
}