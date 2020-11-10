package com.dispositivosmoveis.ritterflix.ui.categoryFilms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dispositivosmoveis.ritterflix.R
import com.dispositivosmoveis.ritterflix.repository.models.PopularMovie
import com.dispositivosmoveis.ritterflix.ui.home.HomeActivity
import com.dispositivosmoveis.ritterflix.ui.home.MovieListener
import kotlinx.android.synthetic.main.fragment_category_films.*
import kotlinx.android.synthetic.main.fragment_home.toolbar
import org.koin.android.viewmodel.ext.android.viewModel

class CategoryFilmsFragment : Fragment() {

    private val viewModel: CategoryFilmsViewModel by viewModel()

    companion object {
        private const val CATEGORY_ID = "category_id"

        fun newInstance(categoryId: Int): CategoryFilmsFragment {
            val args = Bundle()
            args.putSerializable(CATEGORY_ID, categoryId)
            val fragment = CategoryFilmsFragment()
            fragment.arguments = args
            return fragment
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
        return inflater.inflate(R.layout.fragment_category_films, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)

        observeMovies()
    }

    private fun observeMovies() {
        val categoryId = requireArguments().getSerializable(CATEGORY_ID) as Int
        loader_category_films.visibility = View.VISIBLE
        viewModel.getMovies(categoryId.toString()).observe(viewLifecycleOwner, Observer {
            loader_category_films.visibility = View.INVISIBLE
            setupMoviesAdapter(it.results)
        })
    }

    private fun setupMoviesAdapter(movies: List<PopularMovie>) {
        val clickListener = MovieListener {
            (activity as HomeActivity).goToMovieDetail(it.id)
        }
        rv_category_films.adapter = CategoryFilmsAdapter(movies, clickListener)
        rv_category_films.layoutManager =
            LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> activity?.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}