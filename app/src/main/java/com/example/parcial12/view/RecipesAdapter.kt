package com.example.parcial12.view

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial12.R
import com.example.parcial12.databinding.ItemRecipesBinding
import com.example.parcial12.model.Results

class RecipesAdapter(private val onDetailClick: (Int) -> Unit) : RecyclerView.Adapter<RecipesViewHolder>() {
    private var recipes = mutableListOf<Results>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val binding = ItemRecipesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipesViewHolder(binding, onDetailClick)
    }

    override fun getItemCount(): Int = recipes.size

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        var recipe = recipes[position]
        holder.bind(recipe)
    }
    fun updateRecipes(newRecipes: List<Results>){
        recipes.clear()
        recipes.addAll(newRecipes)
        notifyDataSetChanged()
    }

}