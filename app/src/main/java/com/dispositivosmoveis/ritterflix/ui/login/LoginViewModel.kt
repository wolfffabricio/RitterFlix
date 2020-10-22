package com.dispositivosmoveis.ritterflix.ui.login

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel(context: Context): ViewModel() {

    private val _email = MutableLiveData<String>("admin")
    private val _password = MutableLiveData<String>("admin")

    private val LOGIN_PREF = "login_pref"
    private val EMAIL_PREF = "email"
    private val PASSWORD_PREF = "password"
    private var sharedPreferences: SharedPreferences? = null

    init {
        sharedPreferences = context.getSharedPreferences(LOGIN_PREF, Context.MODE_PRIVATE)
    }

    fun validateCredentials(email: String, password: String) : LiveData<Boolean> {
        val isValidCredentials = MutableLiveData<Boolean>()

        if (email.equals(_email.value) && password.equals(_password.value)) {
            isValidCredentials.postValue(true)
        } else {
            isValidCredentials.postValue(false)
        }
        return isValidCredentials
    }

    fun getEmailFromPrefs() : String {
        return sharedPreferences?.getString(EMAIL_PREF, "").toString()
    }

    fun getPasswordFromPrefs() : String {
        return sharedPreferences?.getString(PASSWORD_PREF, "").toString()
    }


    fun saveLogin(email: String, password: String) {
        val editor = sharedPreferences?.edit()
        editor?.putString(EMAIL_PREF, email)
        editor?.putString(PASSWORD_PREF, password)
        editor?.apply()
    }
}