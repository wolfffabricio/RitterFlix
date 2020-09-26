package com.dispositivosmoveis.ritterflix.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dispositivosmoveis.ritterflix.R

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.home_container, HomeFragment.newInstance(), "homeFragment")
                .commit()
        }
    }
}