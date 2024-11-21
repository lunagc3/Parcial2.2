package com.example.parcial12.model

data class RecipeResult(
    val number: Int,
    val offset: Int,
    val results: List<Results>,
    val totalResults: Int
)

data class Results(
    val id: Int,
    val image: String,
    val imageType: String,
    val title: String
)