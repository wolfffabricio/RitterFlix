package com.dispositivosmoveis.ritterflix.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dispositivosmoveis.ritterflix.repository.models.Category
import com.dispositivosmoveis.ritterflix.repository.models.Movie

class HomeViewModel: ViewModel() {

    private var _movies = MutableLiveData<MutableList<Movie>>()
    val movies: LiveData<MutableList<Movie>> get() = _movies

    private var _categories = MutableLiveData<MutableList<Category>>()
    val categories: LiveData<MutableList<Category>> get() = _categories

    init {
        load()
    }

    private fun load() {
        loadMovies()
        loadCategories()
    }

    private fun loadMovies() {
        val movie1 = Movie(name = "Movie 1")
        val movie2 = Movie(name = "Movie 2")
        val movie3 = Movie(name = "Movie 3")
        val movie4 = Movie(name = "Movie 4")
        val movie5 = Movie(name = "Movie 5")
        val movie6 = Movie(name = "Movie 6")
        val movie7 = Movie(name = "Movie 7")
        val movie8 = Movie(name = "Movie 8")
        val movie9 = Movie(name = "Movie 9")
        val movie10 = Movie(name = "Movie 10")

        val moviesList: MutableList<Movie> = arrayListOf()
        moviesList.add(movie1)
        moviesList.add(movie2)
        moviesList.add(movie3)
        moviesList.add(movie4)
        moviesList.add(movie5)
        moviesList.add(movie6)
        moviesList.add(movie7)
        moviesList.add(movie8)
        moviesList.add(movie9)
        moviesList.add(movie10)
        _movies.postValue(moviesList)
    }

    private fun loadCategories() {
        val category1 = Category(name = "Category 1")
        val category2 = Category(name = "Category 2")
        val category3 = Category(name = "Category 3")
        val category4 = Category(name = "Category 4")
        val category5 = Category(name = "Category 5")
        val category6 = Category(name = "Category 6")
        val category7 = Category(name = "Category 7")
        val category8 = Category(name = "Category 8")
        val category9 = Category(name = "Category 9")
        val category10 = Category(name = "Category 10")

        val categoryList: MutableList<Category> = arrayListOf()
        categoryList.add(category1)
        categoryList.add(category2)
        categoryList.add(category3)
        categoryList.add(category4)
        categoryList.add(category5)
        categoryList.add(category6)
        categoryList.add(category7)
        categoryList.add(category8)
        categoryList.add(category9)
        categoryList.add(category10)
        _categories.postValue(categoryList)
    }
}