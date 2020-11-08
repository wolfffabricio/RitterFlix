package com.dispositivosmoveis.ritterflix.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dispositivosmoveis.ritterflix.repository.Repository
import com.dispositivosmoveis.ritterflix.repository.models.Movie

class MovieDetailViewModel: ViewModel() {

    private var repository: Repository? = null
    private var _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> get() = _movie

    init {
        repository = Repository()
    }

    fun getMovieWithId(id: Int): LiveData<Movie>? {
        _movie = repository?.getMovieWithId(id) as MutableLiveData<Movie>
        return _movie
    }
}