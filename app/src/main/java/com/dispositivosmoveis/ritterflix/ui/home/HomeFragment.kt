package com.dispositivosmoveis.ritterflix.ui.home

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dispositivosmoveis.ritterflix.R
import com.dispositivosmoveis.ritterflix.repository.models.Category
import com.dispositivosmoveis.ritterflix.repository.models.Movie
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()

    companion object {
        @JvmStatic
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

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
        val clickListener = SmallMovieListener {
            (activity as HomeActivity).goToMovieDetail(it)
        }
        rv_release.adapter = SmallMoviesAdapter(movies, clickListener)
        rv_release.layoutManager =
            LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setupMoviesAdapter(movies: MutableList<Movie>) {
        val clickListener = MovieListener {
            (activity as HomeActivity).goToMovieDetail(it)
        }
        rv_keep_watching.adapter = MoviesAdapter(movies, clickListener)
        rv_keep_watching.layoutManager =
            LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setupCategoriesAdapter(categories: MutableList<Category>) {
        val clickListener = CategoryListener {
            Toast.makeText(activity?.applicationContext, it.name, Toast.LENGTH_SHORT).show()
        }
        rv_categories.adapter = CategoriesAdapter(categories, clickListener)
        rv_categories.layoutManager = GridLayoutManager(activity?.applicationContext, 2)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.custom_toolbar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> menuAction()
            R.id.search_action -> searchAction()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun menuAction() {
        Toast.makeText(activity?.applicationContext, "Menu", Toast.LENGTH_SHORT).show()
    }

    private fun searchAction() {
        Toast.makeText(activity?.applicationContext, "Search", Toast.LENGTH_SHORT).show()
    }
}