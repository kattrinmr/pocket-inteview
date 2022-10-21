package com.katerina.pocket_interview.home.data.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.katerina.pocket_interview.home.domain.repositories.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(val auth: FirebaseAuth, val firestore: FirebaseFirestore)
    : HomeRepository {

    override fun getAuthInstance(): FirebaseAuth = auth
    override fun getFirestoreInstance(): FirebaseFirestore = firestore

}