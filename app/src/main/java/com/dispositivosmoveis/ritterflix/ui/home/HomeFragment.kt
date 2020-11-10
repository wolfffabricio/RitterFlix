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
import com.dispositivosmoveis.ritterflix.repository.models.Genre
import com.dispositivosmoveis.ritterflix.repository.models.PopularMovie
import com.dispositivosmoveis.ritterflix.repository.models.ReleasedMovie
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

        observeCarouselMovies()
        observeReleasedMovies()
        observePopularMovies()
        observeCategories()
    }

    private fun observeCarouselMovies(){
        val imgs:IntArray = intArrayOf(
            R.drawable.pantera_negra,
            R.drawable.spider_man_far_from_home,
            R.drawable.aquaman,
            R.drawable.jurassic_world
        )

        carousel_view.setImageListener{position, imageView ->
            imageView.setImageResource(imgs[position])
        }
        carousel_view.pageCount = imgs.size
    }

    private fun observeReleasedMovies() {
        viewModel.releasedMovies?.observe(viewLifecycleOwner, Observer {
            setupSmallMoviesAdapter(it.results)
        })
    }

    private fun observePopularMovies() {
        viewModel.popularMovies?.observe(viewLifecycleOwner, Observer {
            setupMoviesAdapter(it.results)
        })
    }

    private fun observeCategories() {
        viewModel.categoriesList?.observe(viewLifecycleOwner, Observer {
            setupCategoriesAdapter(it.genres)
        })
    }

    private fun setupSmallMoviesAdapter(movies: List<ReleasedMovie>) {
        val clickListener = SmallMovieListener {
            (activity as HomeActivity).goToMovieDetail(it.id)
        }
        rv_release.adapter = SmallMoviesAdapter(movies, clickListener)
        rv_release.layoutManager =
            LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setupMoviesAdapter(movies: List<PopularMovie>) {
        val clickListener = MovieListener {
            (activity as HomeActivity).goToMovieDetail(it.id)
        }
        rv_keep_watching.adapter = MoviesAdapter(movies, clickListener)
        rv_keep_watching.layoutManager =
            LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setupCategoriesAdapter(categories: List<Genre>) {
        val clickListener = CategoryListener {
            (activity as HomeActivity).goToCategoryFilmsList(it.id)
        }
        rv_categories.adapter = CategoriesAdapter(categories, clickListener)
        rv_categories.layoutManager = GridLayoutManager(activity?.applicationContext, 2)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> menuAction()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun menuAction() {
        Toast.makeText(activity?.applicationContext, "Menu", Toast.LENGTH_SHORT).show()
    }
}