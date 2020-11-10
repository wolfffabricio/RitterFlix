package com.dispositivosmoveis.ritterflix.ui.categoryFilms

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dispositivosmoveis.ritterflix.R
import com.dispositivosmoveis.ritterflix.databinding.ItemCategoryFilmBinding
import com.dispositivosmoveis.ritterflix.repository.models.PopularMovie
import com.dispositivosmoveis.ritterflix.ui.home.MovieListener

class CategoryFilmsAdapter(val movies: List<PopularMovie>, val clickListener: MovieListener): RecyclerView.Adapter<CategoryFilmsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemCategoryFilmBinding = ItemCategoryFilmBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(clickListener, movies[position])
    }

    class ViewHolder(val binding: ItemCategoryFilmBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: MovieListener, movie: PopularMovie) {
            binding.movie = movie
            binding.clickListener = clickListener
            binding.imgMovie.setImageResource(R.drawable.ic_placeholder)
            binding.executePendingBindings()
        }
    }
}

class MovieListener(val clickListener: (typeId: PopularMovie) -> Unit) {

    fun onClick(type: PopularMovie) {
        clickListener(type)
    }
}