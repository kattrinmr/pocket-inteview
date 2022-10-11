package com.katerina.pocket_interview.core.data.repositories

import com.google.firebase.auth.FirebaseAuth
import com.katerina.pocket_interview.core.data.datasources.UserRemoteDataSource
import com.katerina.pocket_interview.core.domain.models.UserModel
import com.katerina.pocket_interview.core.domain.repositories.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    val auth: FirebaseAuth,
    private val userRemoteDataSource: UserRemoteDataSource
): UserRepository {

    override suspend fun add(user: UserModel) {
        userRemoteDataSource.addUser(user)
    }

    override suspend fun signOut() {
        auth.signOut()
    }
}