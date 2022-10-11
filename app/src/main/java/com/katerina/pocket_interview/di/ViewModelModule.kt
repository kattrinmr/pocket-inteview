package com.katerina.pocket_interview.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.katerina.pocket_interview.core.utils.ViewModelFactory
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ViewModelModule {
    @Binds
    @Singleton
    abstract fun provideViewModelFactory(factory: ViewModelFactory<ViewModel>): ViewModelProvider.Factory
}