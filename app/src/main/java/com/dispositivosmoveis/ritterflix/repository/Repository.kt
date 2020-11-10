package com.dispositivosmoveis.ritterflix.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dispositivosmoveis.ritterflix.repository.http.API_KEY
import com.dispositivosmoveis.ritterflix.repository.http.ApiClient
import com.dispositivosmoveis.ritterflix.repository.http.ApiService
import com.dispositivosmoveis.ritterflix.repository.http.LANGUAGE
import com.dispositivosmoveis.ritterflix.repository.models.Genres
import com.dispositivosmoveis.ritterflix.repository.models.Movie
import com.dispositivosmoveis.ritterflix.repository.models.Popular
import com.dispositivosmoveis.ritterflix.repository.models.Releases
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    private var apiService: ApiService? = null

    fun getReleasedMovies(): LiveData<Releases> {
        val data = MutableLiveData<Releases>()

        apiService?.getLastReleases(API_KEY, LANGUAGE, "1")?.enqueue(object : Callback<Releases> {
            override fun onFailure(call: Call<Releases>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(call: Call<Releases>, response: Response<Releases>) {
                val res = response.body()
                if (response.code() == 200 && res != null) {
                    data.value = res
                } else {
                    data.value = null
                }
            }
        })
        return data
    }

    fun getPopularMovies(): LiveData<Popular> {
        val data = MutableLiveData<Popular>()

        apiService?.getPopularMovies(API_KEY, LANGUAGE, "1")?.enqueue(object : Callback<Popular> {
            override fun onFailure(call: Call<Popular>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(call: Call<Popular>, response: Response<Popular>) {
                val res = response.body()
                if (response.code() == 200 && res != null) {
                    data.value = res
                } else {
                    data.value = null
                }
            }
        })
        return data
    }

    fun getMovieGenres(): LiveData<Genres> {
        val data = MutableLiveData<Genres>()

        apiService?.getGenres("movie", API_KEY, LANGUAGE)?.enqueue(object : Callback<Genres> {

            override fun onFailure(call: Call<Genres>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(call: Call<Genres>, response: Response<Genres>) {
                val res = response.body()
                if (response.code() == 200 && res != null) {
                    data.value = res
                } else {
                    data.value = null
                }
            }
        })
        return data
    }

    fun getMovieWithId(id: Int): LiveData<Movie> {
        val data = MutableLiveData<Movie>()

        apiService?.getMovieWithId(id, API_KEY, LANGUAGE)?.enqueue(object : Callback<Movie> {

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                val res = response.body()

                if (response.code() == 200 && res != null) {
                    data.value = res
                } else {
                    data.value = null
                }
            }
        })
        return data
    }

    fun discoverMovies(genreId: String): LiveData<Popular> {
        val data = MutableLiveData<Popular>()

        apiService?.dicoverMovies(API_KEY, LANGUAGE, 1, true, genreId)?.enqueue(object : Callback<Popular> {
            override fun onFailure(call: Call<Popular>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(call: Call<Popular>, response: Response<Popular>) {
                val res = response.body()
                if (response.code() == 200 && res != null) {
                    data.value = res
                } else {
                    data.value = null
                }
            }
        })
        return data
    }

    init {
        apiService = ApiClient.getApiClient().create(ApiService::class.java)
    }
}