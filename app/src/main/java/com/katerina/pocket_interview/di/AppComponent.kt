package com.katerina.pocket_interview.di

import android.app.Application
import com.katerina.pocket_interview.auth.ui.fragments.AuthFragment
import com.katerina.pocket_interview.auth.ui.fragments.RegistrationFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AuthModule::class, ViewModelModule::class, FirebaseModule::class,
    RegistrationModule::class, UserModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(fragment: AuthFragment)
    fun inject(fragment: RegistrationFragment)

}