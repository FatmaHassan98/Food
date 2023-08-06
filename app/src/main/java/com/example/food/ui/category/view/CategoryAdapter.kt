package com.example.food.ui.category.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.food.data.remote.entity.Category
import com.example.food.databinding.CategoryItemBinding
import com.squareup.picasso.Picasso

class CategoryAdapter (private val onItemCategoryClicked: OnItemCategoryClicked): ListAdapter<Category, CategoryAdapter.ViewHolder>(DiffUtilsCategory()) {

    private lateinit var binding: CategoryItemBinding

    inner class ViewHolder(var binding: CategoryItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        binding = CategoryItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(getItem(position).strCategoryThumb).into(holder.binding.imageCategory)
        holder.binding.category = getItem(position)
        holder.binding.categoryCard.setOnClickListener {
            onItemCategoryClicked.categoryClicked(getItem(position).strCategory ?: "")
        }
    }

}

class DiffUtilsCategory() : DiffUtil.ItemCallback<Category>() {

    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }

}

interface OnItemCategoryClicked{
    fun categoryClicked(category: String)
}
