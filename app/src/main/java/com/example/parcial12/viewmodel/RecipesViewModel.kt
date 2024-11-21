package com.example.parcial12.viewmodel

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parcial12.model.RecipeIDResult
import com.example.parcial12.model.RecipeRepository
import com.example.parcial12.model.RecipeResult
import com.example.parcial12.model.Results
import com.squareup.picasso.BuildConfig
import kotlinx.coroutines.launch
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
    fun fetchRecipeDetails(apiKey: String, recipeId: Int) : LiveData<RecipeIDResult?> {
        viewModelScope.launch {
            try {
                val response : Response<RecipeIDResult> = repository.getRecipeDetails(apiKey, recipeId)
                if(response.isSuccessful){
                    _recipeDetails.value = response.body()
                }else{
                    throw Exception("error al solicitar detalles")
                }
            } catch (e: Exception) {
                error.postValue("Hubo un error")
            }
        }
        return recipeDetails
    }


}