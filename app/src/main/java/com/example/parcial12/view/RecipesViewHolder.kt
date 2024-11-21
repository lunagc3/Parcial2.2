package com.example.parcial12.view

import android.app.AlertDialog
import android.content.Intent
import android.view.View
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial12.databinding.ItemRecipesBinding
import com.example.parcial12.model.RecipeIDResult
import com.example.parcial12.model.Results
import com.squareup.picasso.Picasso

class RecipesViewHolder(private val binding : ItemRecipesBinding,
        private val onDetailClick: (Int) -> Unit) : RecyclerView.ViewHolder(binding.root) {
    fun bind(recipe: Results){
        binding.tvTitle.text = recipe.title
        Picasso.get().load(recipe.image).into(binding.ivRecipes)
        binding.btnSearch.setOnClickListener()
        {
            val builder = AlertDialog.Builder(itemView.context)
            builder.setTitle("Confirmar Búsqueda")
            builder.setMessage("Ver detalles de esta receta?")
            builder.setPositiveButton("Sí") { dialog, which ->
                onDetailClick(recipe.id)
            }
            builder.setNegativeButton("Cancelar") { dialog, which ->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }
    }

}
