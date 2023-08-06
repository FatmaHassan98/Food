package com.example.food.ui.meals.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.Meal
import com.example.food.databinding.MealItemBinding
import com.squareup.picasso.Picasso

class MealAdapter: ListAdapter<Meal, MealAdapter.ViewHolder>(DiffUtilsMeal()) {

    private lateinit var binding: MealItemBinding

    inner class ViewHolder(var binding: MealItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        binding = MealItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(getItem(position).strMealThumb).into(holder.binding.imageMeal)
        holder.binding.meal = getItem(position)
    }

}

class DiffUtilsMeal() : DiffUtil.ItemCallback<Meal>() {

    override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
        return oldItem == newItem
    }

}
