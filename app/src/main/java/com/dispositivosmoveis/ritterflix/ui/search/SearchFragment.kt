package com.dispositivosmoveis.ritterflix.ui.search

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dispositivosmoveis.ritterflix.R
import com.dispositivosmoveis.ritterflix.repository.models.PopularMovie
import com.dispositivosmoveis.ritterflix.ui.home.HomeActivity
import com.dispositivosmoveis.ritterflix.ui.home.MovieListener
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.viewmodel.ext.android.viewModel


class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModel()

    companion object {
        @JvmStatic
        fun newInstance(): SearchFragment {
            return SearchFragment()
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
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(toolbar_search)
        showPlaceholder(true, getString(R.string.search_here))
    }

    private fun searchMovie(value: String) {
        showPlaceholder(false)
        showLoader(true)
        viewModel.searchMovie(value).observe(viewLifecycleOwner, Observer {
            showLoader(false)

            if (it.results.isNotEmpty()) {
                setupMoviesAdapter(it.results)
                showPlaceholder(false)
            } else {
                showPlaceholder(true, getString(R.string.result_not_found))
            }
        })
    }

    private fun setupMoviesAdapter(movies: List<PopularMovie>) {
        val clickListener = MovieListener {
            (activity as HomeActivity).goToMovieDetail(it.id)
        }
        rv_search.adapter = SearchAdapter(movies, clickListener)
        rv_search.layoutManager =
            LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
    }

    private fun showPlaceholder(show: Boolean, message: String = "") {
        tv_placeholder.visibility = if (show) View.VISIBLE else View.INVISIBLE
        rv_search.visibility = if (!show) View.VISIBLE else View.INVISIBLE
        tv_placeholder.text = message
    }

    private fun showLoader(show: Boolean) {
        loader_search.visibility = if (show) View.VISIBLE else View.INVISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_search, menu)
        val item: MenuItem = menu.findItem(R.id.action_search)
        val searchView = (context as HomeActivity).supportActionBar?.themedContext?.let { SearchView(it) }

        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)
        item.actionView = searchView
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                searchMovie(query ?: "")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
}