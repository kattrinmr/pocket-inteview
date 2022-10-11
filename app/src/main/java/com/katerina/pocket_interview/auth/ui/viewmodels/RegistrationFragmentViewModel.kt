package com.katerina.pocket_interview.auth.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.katerina.pocket_interview.auth.domain.interactors.RegistrationInteractor
import com.katerina.pocket_interview.core.utils.responses.ResponseStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegistrationFragmentViewModel @Inject constructor(
    private val registrationInteractor: RegistrationInteractor
): ViewModel() {

    private val registrationStatus: MutableLiveData<ResponseStatus> by lazy {
        MutableLiveData<ResponseStatus>()
    }

    val registrationObservable: LiveData<ResponseStatus>
        get() = registrationStatus

    fun createUser(email: String, password: String, confirmPassword: String) {
        registrationStatus.value = ResponseStatus.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = registrationInteractor.registerUser(email, password, confirmPassword).await()
                withContext(Dispatchers.Main) {
                    registrationStatus.value = ResponseStatus.Success()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    registrationStatus.value = ResponseStatus.Error(e)
                }
            }
        }
    }

}