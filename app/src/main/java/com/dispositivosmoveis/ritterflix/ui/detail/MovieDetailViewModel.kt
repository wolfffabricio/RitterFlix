package com.dispositivosmoveis.ritterflix.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dispositivosmoveis.ritterflix.repository.Repository
import com.dispositivosmoveis.ritterflix.repository.models.Movie

class MovieDetailViewModel: ViewModel() {

    private var repository: Repository? = null
    private var movie: LiveData<Movie>? = null

    init {
        repository = Repository()
        movie = MutableLiveData()
    }

    fun getMovieWithId(id: Int): LiveData<Movie>? {
        return repository?.getMovieWithId(id)
    }
}