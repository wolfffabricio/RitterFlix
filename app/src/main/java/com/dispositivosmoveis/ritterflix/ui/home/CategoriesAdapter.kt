package com.dispositivosmoveis.ritterflix.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dispositivosmoveis.ritterflix.R
import com.dispositivosmoveis.ritterflix.databinding.ItemCategoryBinding
import com.dispositivosmoveis.ritterflix.repository.models.Genre

class CategoriesAdapter(
    private val categories: List<Genre>,
    private val clickListener: CategoryListener
) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemCategoryBinding = ItemCategoryBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(clickListener, categories[position])
    }

    class ViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: CategoryListener, category: Genre) {
            binding.category = category
            binding.clickListener = clickListener
            binding.imgCategory.setImageResource(R.drawable.ic_placeholder)
            binding.executePendingBindings()
        }
    }
}

class CategoryListener(val clickListener: (typeId: Genre) -> Unit) {

    fun onClick(type: Genre) {
        clickListener(type)
    }
}