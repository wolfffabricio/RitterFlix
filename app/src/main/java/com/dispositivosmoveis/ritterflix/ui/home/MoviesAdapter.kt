package com.dispositivosmoveis.ritterflix.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dispositivosmoveis.ritterflix.R
import com.dispositivosmoveis.ritterflix.databinding.ItemMovieBinding
import com.dispositivosmoveis.ritterflix.repository.models.Movie

class MoviesAdapter(val movies: MutableList<Movie>): RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemMovieBinding = ItemMovieBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    class ViewHolder(val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movie = movie
            binding.imgMovie.setImageResource(R.drawable.ic_placeholder)
            binding.executePendingBindings()
        }
    }
}