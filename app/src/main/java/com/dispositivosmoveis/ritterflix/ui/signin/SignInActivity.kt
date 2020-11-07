package com.dispositivosmoveis.ritterflix.ui.signin

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.dispositivosmoveis.ritterflix.R
import com.dispositivosmoveis.ritterflix.ui.login.LoginFragment

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        setSupportActionBar(findViewById(R.id.toolbar))

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.sign_in_container, SignInFragment.newInstance(), "signinfragment")
                .commit()
        }
    }
}