package com.example.parcial12.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.semantics.text
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parcial12.BuildConfig
import com.example.parcial12.databinding.FragmentRecipesBinding
import com.example.parcial12.model.RecipeRepository
import com.example.parcial12.model.RetrofitInstance
import com.example.parcial12.viewmodel.RecipesViewModel
import com.squareup.picasso.Picasso

class FragmentRecipes : Fragment() {
    private lateinit var binding: FragmentRecipesBinding
    private lateinit var adapter: RecipesAdapter
    private lateinit var viewModel: RecipesViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipesBinding.inflate(inflater, container, false)
        val apiKey = BuildConfig.API_KEY
        val repository = RecipeRepository(RetrofitInstance.api)

        adapter = RecipesAdapter { recipeId ->
            onDetailClick(recipeId)
        }

        binding.rvRecipes.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvRecipes.adapter = adapter

        viewModel = ViewModelProvider(requireActivity()).get(RecipesViewModel::class.java).apply {
            this.repository = repository
        }
        viewModel.fetchRecipes(apiKey,null)

        viewModel.recipes.observe(viewLifecycleOwner, { recipe->
            adapter.updateRecipes(recipe)
        })

        return binding.root
    }
fun onDetailClick(recipeId: Int) {
      //  Toast.makeText(requireContext(), "ID de la receta: $recipeId", Toast.LENGTH_SHORT).show()
        viewModel.fetchRecipeDetails(BuildConfig.API_KEY, recipeId)
            viewModel.recipeDetails.observe(viewLifecycleOwner) { recipeDetails ->
                binding.recipeDetailContainer.visibility = View.VISIBLE
                binding.tvRecipeName.text = recipeDetails?.title
                binding.tvRecipeDescription.text = recipeDetails?.summary
                Picasso.get().load(recipeDetails?.image).into(binding.ivRecipe)
                println("Detalles de la receta: ${recipeDetails?.title}")
        }

    }
}
