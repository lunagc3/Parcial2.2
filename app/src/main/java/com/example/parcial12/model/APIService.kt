package com.example.parcial12.model

import com.example.parcial12.BuildConfig
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("recipes/{id}/information")
    suspend fun getRecipesById(
        @Path("id") id: Int,
        @Query("apiKey") apiKey:String
   ):Response<RecipeIDResult>
    @GET("recipes/complexSearch")
    suspend fun getRecipes(
        @Query("apiKey") apiKey:String = BuildConfig.API_KEY,
        @Query("query") number: String?
    ): Response<RecipeResult>
}

object RetrofitInstance{
    val api  : APIService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)
    }

}
