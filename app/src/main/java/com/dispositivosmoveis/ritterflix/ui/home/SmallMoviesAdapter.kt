package com.dispositivosmoveis.ritterflix.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dispositivosmoveis.ritterflix.R
import com.dispositivosmoveis.ritterflix.databinding.ItemSmallMovieBinding
import com.dispositivosmoveis.ritterflix.repository.models.ReleasedMovie

class SmallMoviesAdapter(
    var movies: List<ReleasedMovie>,
    private val clickListener: SmallMovieListener
) : RecyclerView.Adapter<SmallMoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemSmallMovieBinding = ItemSmallMovieBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(clickListener, movies[position])
    }

    class ViewHolder(val binding: ItemSmallMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: SmallMovieListener, movie: ReleasedMovie) {
            binding.movie = movie
            binding.imgMovie.setImageResource(R.drawable.ic_placeholder)
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }
}

class SmallMovieListener(val clickListener: (typeId: ReleasedMovie) -> Unit) {

    fun onClick(type: ReleasedMovie) {
        clickListener(type)
    }
}