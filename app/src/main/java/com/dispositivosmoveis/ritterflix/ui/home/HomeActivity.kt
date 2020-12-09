package com.dispositivosmoveis.ritterflix.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dispositivosmoveis.ritterflix.R
import com.dispositivosmoveis.ritterflix.ui.categoryFilms.CategoryFilmsFragment
import com.dispositivosmoveis.ritterflix.ui.detail.MovieDetailFragment
import com.dispositivosmoveis.ritterflix.ui.more.MoreFragment
import com.dispositivosmoveis.ritterflix.ui.search.SearchFragment
import kotlinx.android.synthetic.main.activity_home.*

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
        val searchFragment =
            SearchFragment()
        val moreFragment =
            MoreFragment()

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

    override fun onResume() {
        super.onResume()

        val intent = intent
        if (Intent.ACTION_VIEW == intent.action) {
            val uri = intent.data
            val id = uri!!.getQueryParameter("id")
            goToMovieDetail(id?.toInt() ?: 0, false)
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

    fun goToMovieDetail(movieId: Int, addToBackStack: Boolean = true) {
        if (addToBackStack) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.home_container, MovieDetailFragment.newInstance(movieId), "detailFragment")
                .addToBackStack(null)
                .commit()
        } else {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.home_container, MovieDetailFragment.newInstance(movieId), "detailFragment")
                .commit()
        }
    }

    fun goToCategoryFilmsList(categoryId: Int) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.home_container, CategoryFilmsFragment.newInstance(categoryId), "categoryFilmsFragment")
            .addToBackStack(null)
            .commit()
    }
}