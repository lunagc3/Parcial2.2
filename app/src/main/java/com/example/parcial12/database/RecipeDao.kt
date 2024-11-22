package com.example.parcial12.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface  RecipeDao {
    @Insert
    fun insert(recipe: RecipeEntity)

    @Query("SELECT * FROM recipes")
    fun getAllRecipes(): List<RecipeEntity>

    @Delete
    fun delete(recipe: RecipeEntity)

}
