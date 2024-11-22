package com.example.parcial12.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsetsAnimation
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Database
import androidx.room.Room
import com.example.parcial12.BuildConfig
import com.example.parcial12.R
import com.example.parcial12.database.AppDatabase
import com.example.parcial12.database.RecipeEntity
import com.example.parcial12.databinding.ActivityMainBinding
import com.example.parcial12.databinding.ActivitySecondBinding
import com.example.parcial12.model.RecipeIDResult
import com.example.parcial12.model.RecipeRepository
import com.example.parcial12.model.RetrofitInstance
import com.example.parcial12.viewmodel.RecipesViewModel
import com.squareup.picasso.Picasso
import javax.security.auth.callback.Callback

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var viewModel: RecipesViewModel
    private lateinit var adapter: RecipesHAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(RecipesViewModel::class.java)
        viewModel.repository = RecipeRepository(RetrofitInstance.api)

        val apiKey = BuildConfig.API_KEY
        val repository = RecipeRepository(RetrofitInstance.api)

        viewModel = ViewModelProvider(this).get(RecipesViewModel::class.java).apply {
            this.repository = repository
        }
        adapter = RecipesHAdapter()

        viewModel.fetchRecipes(apiKey,null)

        viewModel.recipes.observe(this, { recipe->
            adapter.updateRecipes(recipe)
        })

        binding.rvRecipes.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvRecipes.adapter = adapter



        binding.btnMa.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}