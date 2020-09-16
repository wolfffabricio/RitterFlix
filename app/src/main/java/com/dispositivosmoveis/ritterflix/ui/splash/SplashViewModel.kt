package com.dispositivosmoveis.ritterflix.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel: ViewModel() {

    private val mutableLiveData = MutableLiveData<SplashState>()
    val liveData: LiveData<SplashState> get() = mutableLiveData

    init {
        GlobalScope.launch {
            delay(4000)
            mutableLiveData.postValue(SplashState.LoginActivity())
        }
    }
 }

sealed class SplashState {
    class LoginActivity : SplashState()
}