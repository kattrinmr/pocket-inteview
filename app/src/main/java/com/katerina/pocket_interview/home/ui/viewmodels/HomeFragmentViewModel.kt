package com.katerina.pocket_interview.home.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.katerina.pocket_interview.home.domain.interactors.HomeInteractor
import javax.inject.Inject

class HomeFragmentViewModel @Inject constructor(val homeInteractor: HomeInteractor)
    : ViewModel() {

    fun getCurrentUser() = homeInteractor.getCurrentUser()
    fun getDb() = homeInteractor.getDb()
}