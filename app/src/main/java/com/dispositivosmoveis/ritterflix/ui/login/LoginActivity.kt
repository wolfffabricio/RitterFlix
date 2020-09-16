package com.dispositivosmoveis.ritterflix.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dispositivosmoveis.ritterflix.R
import com.dispositivosmoveis.ritterflix.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_button.setOnClickListener {
            finish()
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}