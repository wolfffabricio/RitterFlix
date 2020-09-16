package com.dispositivosmoveis.ritterflix.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dispositivosmoveis.ritterflix.R
import com.dispositivosmoveis.ritterflix.repository.models.Category
import com.dispositivosmoveis.ritterflix.repository.models.Movie
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        observeMovies()
        observeCategories()
    }

    private fun observeMovies() {
        viewModel.movies.observe(this, Observer {
            it.let {
                setupSmallMoviesAdapter(it)
                setupMoviesAdapter(it)
            }
        })
    }

    private fun observeCategories() {
        viewModel.categories.observe(this, Observer {
            it.let {
                setupCategoriesAdapter(it)
            }
        })
    }

    private fun setupSmallMoviesAdapter(movies: MutableList<Movie>) {
        rv_release.adapter = SmallMoviesAdapter(movies)
        rv_release.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setupMoviesAdapter(movies: MutableList<Movie>) {
        rv_keep_watching.adapter = MoviesAdapter(movies)
        rv_keep_watching.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setupCategoriesAdapter(categories: MutableList<Category>) {
        rv_categories.adapter = CategoriesAdapter(categories)
        rv_categories.layoutManager = GridLayoutManager(this, 2)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.custom_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> menuAction()
            R.id.search_action -> searchAction()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun menuAction() {
        Toast.makeText(this, "Menu", Toast.LENGTH_SHORT).show()
    }

    private fun searchAction() {
        Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show()
    }
}