package com.katerina.pocket_interview.core.data.datasources

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.katerina.pocket_interview.core.domain.models.UserModel
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(dbRef: FirebaseFirestore) {

    private val db = dbRef

    suspend fun addUser(userModel: UserModel) {
        db.collection("users")
            .add(userModel)
            .addOnSuccessListener {
                Log.d("addUser", "Snapshot added with ID: ${it.id}")
            }
            .addOnFailureListener {
                Log.d("addUser", "Error adding snapshot", it)
            }
    }
}