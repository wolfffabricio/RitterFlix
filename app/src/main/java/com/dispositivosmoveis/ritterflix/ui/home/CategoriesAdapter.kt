package com.dispositivosmoveis.ritterflix.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dispositivosmoveis.ritterflix.R
import com.dispositivosmoveis.ritterflix.databinding.ItemCategoryBinding
import com.dispositivosmoveis.ritterflix.repository.models.Category

class CategoriesAdapter(private val categories: MutableList<Category>): RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemCategoryBinding = ItemCategoryBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    class ViewHolder(private val binding: ItemCategoryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            binding.category = category
            binding.imgCategory.setImageResource(R.drawable.ic_placeholder)
            binding.executePendingBindings()
        }
    }
}