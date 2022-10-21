package com.katerina.pocket_interview.auth.data.repositories

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.katerina.pocket_interview.auth.domain.repositories.RegistrationRepository
import javax.inject.Inject

class RegistrationRepositoryImpl @Inject constructor(val auth: FirebaseAuth, val db: FirebaseFirestore)
    : RegistrationRepository {

    override suspend fun createUserByEmailAndPassword(
        email: String,
        password: String
    ): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email, password)
    }

    override suspend fun addCurrentUserInitData(fullname: String, email: String) {

    }
}