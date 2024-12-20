package com.example.parcial12.viewmodel

import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parcial12.database.RecipeDao
import com.example.parcial12.database.RecipeEntity
import com.example.parcial12.model.RecipeIDResult
import com.example.parcial12.model.RecipeRepository
import com.example.parcial12.model.RecipeResult
import com.example.parcial12.model.Results
import com.squareup.picasso.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response


class RecipesViewModel() : ViewModel(){
    private var _recipes = MutableLiveData<List<Results>>()
    val recipes: LiveData<List<Results>> get() = _recipes
    val error = MutableLiveData<String>()
    lateinit var repository: RecipeRepository
    private val _recipeDetails = MutableLiveData<RecipeIDResult?>()
    val recipeDetails: LiveData<RecipeIDResult?> = _recipeDetails
    fun fetchRecipes(apiKey : String, query : String?) {
        viewModelScope.launch{
            try{
                val _repository = repository.getRecipesFromAPI(apiKey,query)
                _recipes.value = _repository
            } catch(e : Exception){
                error.postValue("Hubo un error")
            }
        }
    }
  fun fetchRecipeDetails(apiKey: String, recipeId: Int) {
        viewModelScope.launch (Dispatchers.IO){
            try {
                val response : Response<RecipeIDResult> = repository.getRecipeDetails(apiKey, recipeId)
                if(response.isSuccessful){
                    withContext(Dispatchers.Main) {
                        _recipeDetails.postValue(response.body())
                        println("Detalles de la receta: ${response.body()}")
                    }
                }else{
                    throw Exception("error al solicitar detalles")
                }
            } catch (e: Exception) {
                error.postValue("Hubo un error")
                println("Error: ${e.message}")
            }
        }
    }

}