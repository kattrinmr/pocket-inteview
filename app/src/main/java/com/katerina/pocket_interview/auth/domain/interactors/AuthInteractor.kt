package com.katerina.pocket_interview.auth.domain.interactors

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.katerina.pocket_interview.auth.domain.repositories.AuthRepository
import javax.inject.Inject

class AuthInteractor @Inject constructor(val repository: AuthRepository) {

    suspend fun logIn(email: String, password: String): Task<AuthResult> {
        if (validation(email, password))
            return repository.authByEmailEndPassword(email, password)
        else throw IllegalStateException("Fields should not be empty")
    }

    private fun validation(email: String, password: String) = !(email.isEmpty() or password.isEmpty())
}