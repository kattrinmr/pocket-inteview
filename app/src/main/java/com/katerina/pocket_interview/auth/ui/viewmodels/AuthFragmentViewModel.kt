package com.katerina.pocket_interview.auth.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.katerina.pocket_interview.auth.domain.interactors.AuthInteractor
import com.katerina.pocket_interview.core.utils.responses.ResponseStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthFragmentViewModel @Inject constructor(val authInteractor: AuthInteractor): ViewModel() {

    private val authStatus: MutableLiveData<ResponseStatus> by lazy {
        MutableLiveData<ResponseStatus>()
    }

    val authObservable: LiveData<ResponseStatus>
        get() = authStatus

    fun auth(email: String, password: String) {
        authStatus.value = ResponseStatus.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = authInteractor.logIn(email, password).await()
                withContext(Dispatchers.Main) {
                    authStatus.value = ResponseStatus.Success()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    authStatus.value = ResponseStatus.Error(e)
                }
            }
        }
    }
}