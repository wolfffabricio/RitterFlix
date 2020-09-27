package com.dispositivosmoveis.ritterflix.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dispositivosmoveis.ritterflix.R
import com.dispositivosmoveis.ritterflix.repository.models.Movie
import com.dispositivosmoveis.ritterflix.ui.detail.MovieDetailFragment

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

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    fun goToMovieDetail(movie: Movie) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.home_container, MovieDetailFragment.newInstance(movie), "detailFragment")
            .addToBackStack(null)
            .commit()
    }
}