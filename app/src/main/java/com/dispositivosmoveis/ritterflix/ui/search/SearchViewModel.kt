package com.dispositivosmoveis.ritterflix.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dispositivosmoveis.ritterflix.repository.Repository
import com.dispositivosmoveis.ritterflix.repository.models.Popular

class SearchViewModel: ViewModel() {

    private var repository: Repository? = null
    private var movies = MutableLiveData<Popular>()

    init {
        repository = Repository()
    }

    fun searchMovie(value: String): LiveData<Popular> {
        movies = repository?.searchMovies(value) as MutableLiveData<Popular>
        return movies
    }
}