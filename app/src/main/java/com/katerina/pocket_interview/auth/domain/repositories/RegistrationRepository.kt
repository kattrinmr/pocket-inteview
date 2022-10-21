package com.katerina.pocket_interview.auth.domain.repositories

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface RegistrationRepository {

    suspend fun createUserByEmailAndPassword(email: String, password: String): Task<AuthResult>

    suspend fun addCurrentUserInitData(fullname: String, email: String)
}