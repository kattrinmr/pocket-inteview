package com.katerina.pocket_interview.home.domain.interactors

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.katerina.pocket_interview.home.domain.repositories.HomeRepository
import javax.inject.Inject

class HomeInteractor @Inject constructor(
    val homeRepository: HomeRepository
) {

    fun getCurrentUser(): FirebaseAuth {
        return homeRepository.getAuthInstance()
    }

    fun getDb(): FirebaseFirestore {
        return homeRepository.getFirestoreInstance()
    }

}