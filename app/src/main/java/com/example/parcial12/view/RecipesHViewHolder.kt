package com.example.parcial12.view

import android.app.AlertDialog
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial12.database.AppDatabase
import com.example.parcial12.database.RecipeEntity
import com.example.parcial12.databinding.ItemHorizontalRecipesBinding
import com.example.parcial12.databinding.ItemRecipesBinding
import com.example.parcial12.model.Results

import com.squareup.picasso.Picasso

class RecipesHViewHolder(private val binding : ItemHorizontalRecipesBinding) : RecyclerView.ViewHolder(binding.root)
{

    fun bind(recipe: Results){
        binding.tvTitle.text = recipe.title
        Picasso.get().load(recipe.image).into(binding.ivRecipes)

        binding.btnSave.setOnClickListener() {
            val builder = AlertDialog.Builder(itemView.context)
            builder.setTitle("Confirmar Guardado")
            builder.setMessage("Quiere guardar esta receta?")
            builder.setPositiveButton("Sí") { dialog, which ->
                Toast.makeText(binding.root.context, "Receta guardada", Toast.LENGTH_SHORT).show()
                val recipeEntity = RecipeEntity(recipe.id, recipe.title, recipe.image)
                val recipeDao = AppDatabase.getDatabase(binding.root.context).recipeDao()
                recipeDao.insert(recipeEntity)
            }
            builder.setNegativeButton("Cancelar") { dialog, which ->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }
    }

}