package com.katerina.pocket_interview.core.domain.repositories

import com.katerina.pocket_interview.core.domain.models.UserModel

interface UserRepository {

    suspend fun add(user: UserModel)
    suspend fun signOut()
}