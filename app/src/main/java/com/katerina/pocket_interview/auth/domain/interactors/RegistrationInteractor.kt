package com.katerina.pocket_interview.auth.domain.interactors

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.katerina.pocket_interview.auth.domain.repositories.RegistrationRepository
import com.katerina.pocket_interview.core.domain.repositories.UserRepository
import javax.inject.Inject

class RegistrationInteractor @Inject constructor(
    private val registrationRepository: RegistrationRepository,
) {

    suspend fun registerUser(email: String, password: String, confirmPassword: String): Task<AuthResult> {
        if (validation(email, password, confirmPassword))
            return registrationRepository.createUserByEmailAndPassword(email, password)
        else throw IllegalStateException("Fields are empty or passwords don't match!!!")
    }

    private fun validation(email: String, password: String, confirmPassword: String): Boolean {
        if (email.isEmpty() or password.isEmpty() or confirmPassword.isEmpty()) return false
        if (password != confirmPassword) return false
        return true
    }
}