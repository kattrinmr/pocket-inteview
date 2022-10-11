package com.katerina.pocket_interview.auth.domain.repositories

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface AuthRepository {
    suspend fun authByEmailEndPassword(email: String, password: String): Task<AuthResult>
}