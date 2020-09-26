package com.dispositivosmoveis.ritterflix.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dispositivosmoveis.ritterflix.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.login_container, LoginFragment.newInstance(), "loginFragment")
                .commit()
        }
    }
}