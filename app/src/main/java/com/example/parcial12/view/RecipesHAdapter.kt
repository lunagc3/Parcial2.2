package com.example.parcial12.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial12.R
import com.example.parcial12.databinding.ItemHorizontalRecipesBinding
import com.example.parcial12.databinding.ItemRecipesBinding
import com.example.parcial12.model.Results


class RecipesHAdapter() : RecyclerView.Adapter<RecipesHViewHolder>() {
    private var recipes = mutableListOf<Results>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesHViewHolder {
        val binding = ItemHorizontalRecipesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipesHViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipesHViewHolder, position: Int) {
        var recipe = recipes[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int = recipes.size


    fun updateRecipes(newRecipes: List<Results>){
        recipes.clear()
        recipes.addAll(newRecipes)
        notifyDataSetChanged()
    }
}