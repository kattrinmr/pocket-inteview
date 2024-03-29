package com.katerina.pocket_interview.core.utils

import android.content.Context
import androidx.startup.Initializer
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.katerina.pocket_interview.BuildConfig

class FirestoreInitializer : Initializer<FirebaseFirestore> {

    private val FIRESTORE_EMULATOR_HOST = "10.0.2.2"
    private val FIRESTORE_EMULATOR_PORT = 8080

    override fun create(context: Context): FirebaseFirestore {
        val firestore = Firebase.firestore
        if (BuildConfig.DEBUG) {
            firestore.useEmulator(FIRESTORE_EMULATOR_HOST, FIRESTORE_EMULATOR_PORT)
        }
        return firestore
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}