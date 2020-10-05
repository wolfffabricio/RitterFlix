package com.dispositivosmoveis.ritterflix.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dispositivosmoveis.ritterflix.repository.Repository
import com.dispositivosmoveis.ritterflix.repository.models.Genres
import com.dispositivosmoveis.ritterflix.repository.models.Movie
import com.dispositivosmoveis.ritterflix.repository.models.Popular
import com.dispositivosmoveis.ritterflix.repository.models.Releases

class HomeViewModel: ViewModel() {

    private var repository: Repository? = null

    var releasedMovies: LiveData<Releases>? = null
    var popularMovies: LiveData<Popular>? = null
    var categoriesList: LiveData<Genres>? = null

    init {
        repository = Repository()
        releasedMovies = MutableLiveData()
        popularMovies = MutableLiveData()
        categoriesList = MutableLiveData()
        load()
    }

    private fun load() {
        loadReleasedMovies()
        loadPopularMovies()
        loadCategories()
    }

    private fun loadReleasedMovies() {
        releasedMovies = repository?.getReleasedMovies()
    }

    private fun loadPopularMovies() {
        popularMovies = repository?.getPopularMovies()
    }

    private fun loadCategories() {
        categoriesList = repository?.getMovieGenres()
    }
}