package com.dispositivosmoveis.ritterflix.ui.home

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import androidx.fragment.app.Fragment
import com.dispositivosmoveis.ritterflix.R
import com.dispositivosmoveis.ritterflix.repository.models.Movie
import com.dispositivosmoveis.ritterflix.repository.models.ReleasedMovie
import com.dispositivosmoveis.ritterflix.ui.detail.MovieDetailFragment
import com.synnapps.carouselview.CarouselView
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_search.*

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

        val homeFragment = HomeFragment()
        val searchFragment = SearchFragment()
        val moreFragment = MoreFragment()

        makeCurrentFragment(homeFragment)

        bottom_navigation.setOnNavigationItemSelectedListener{
            when (it.itemId){
                R.id.ic_home_nav -> makeCurrentFragment(homeFragment)
                R.id.ic_search_nav -> makeCurrentFragment(searchFragment)
                R.id.ic_more_nav -> makeCurrentFragment(moreFragment)
            }
            true
        }

    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.home_container, fragment)
            commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    fun goToMovieDetail(movieId: Int) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.home_container, MovieDetailFragment.newInstance(movieId), "detailFragment")
            .addToBackStack(null)
            .commit()
    }
}