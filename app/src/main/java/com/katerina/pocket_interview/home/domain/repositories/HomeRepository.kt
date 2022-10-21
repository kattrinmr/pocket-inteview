package com.katerina.pocket_interview.home.domain.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

interface HomeRepository {

    fun getAuthInstance(): FirebaseAuth
    fun getFirestoreInstance(): FirebaseFirestore


}