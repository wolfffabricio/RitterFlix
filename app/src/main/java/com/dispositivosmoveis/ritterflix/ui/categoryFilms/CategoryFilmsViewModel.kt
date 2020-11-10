package com.dispositivosmoveis.ritterflix.ui.categoryFilms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dispositivosmoveis.ritterflix.repository.Repository
import com.dispositivosmoveis.ritterflix.repository.models.Popular

class CategoryFilmsViewModel: ViewModel() {

    private var repository = Repository()
    private var _movies = MutableLiveData<Popular>()

    fun getMovies(genreId: String): LiveData<Popular> {
        _movies = repository.discoverMovies(genreId) as MutableLiveData<Popular>
        return _movies
    }
}