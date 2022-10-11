package com.katerina.pocket_interview

import android.app.Application
import com.google.firebase.FirebaseApp
import com.katerina.pocket_interview.di.AppComponent
import com.katerina.pocket_interview.di.DaggerAppComponent

class MyApplication : Application() {

    val appComponent: AppComponent = DaggerAppComponent.builder().application(this).build()

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}