package com.example.parcial12.model

import retrofit2.Response
import kotlin.Result

class RecipeRepository (private val apiService: APIService) {
        suspend fun getRecipesFromAPI(apiKey: String, query: String?): List<Results> {
                val response = apiService.getRecipes(apiKey, query)

                if (response.isSuccessful) {
                        return response.body()?.results ?: emptyList()
                } else {
                        throw Exception("Hubo un error en la solicitud")
                }
        }

        suspend fun getRecipeDetails(apiKey: String, recipeId: Int): Response<RecipeIDResult> {
                val response = apiService.getRecipesById(recipeId, apiKey)
                if (response.isSuccessful) {
                        return response
                } else {
                        throw Exception("Hubo un error en solicitud de detalles")
                }
        }
}
