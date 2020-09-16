package com.dispositivosmoveis.ritterflix.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.dispositivosmoveis.ritterflix.R
import com.dispositivosmoveis.ritterflix.ui.login.LoginActivity
import org.koin.android.viewmodel.ext.android.viewModel

class SplashScreenActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()

        observeLaunch()
    }

    private fun observeLaunch() {
        viewModel.liveData.observe(this, Observer {
            when(it) {
                is SplashState.LoginActivity -> {
                    goToLoginActivity()
                }
            }
        })
    }

    private fun goToLoginActivity() {
        finish()
        startActivity(Intent(this, LoginActivity::class.java))
    }
}