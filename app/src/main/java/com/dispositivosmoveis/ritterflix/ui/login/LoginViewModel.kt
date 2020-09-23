package com.dispositivosmoveis.ritterflix.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    private val _email = MutableLiveData<String>("admin")
    private val _password = MutableLiveData<String>("admin")

    fun validateCredentials(email: String, password: String) : LiveData<Boolean> {
        val isValidCredentials = MutableLiveData<Boolean>()

        if (email.equals(_email.value) && password.equals(_password.value)) {
            isValidCredentials.postValue(true)
        } else {
            isValidCredentials.postValue(false)
        }
        return isValidCredentials
    }
}