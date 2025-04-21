package com.example.recipeapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.recipeapp.activities.DetailActivity
import com.example.recipeapp.adapters.RecipeCardAdapter
import com.example.recipeapp.api.RECIPE_ID_EXTRA
import com.example.recipeapp.api.Recipe
import com.example.recipeapp.api.RecipeResponse
import com.example.recipeapp.api.RestClient
import com.example.recipeapp.databinding.ActivityMainBinding
import com.example.recipeapp.helpers.RecipeClickListener
import com.example.recipeapp.helpers.SwipeToDeleteCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), RecipeClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecipeCardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        RestClient.init()

        val mainActivity = this

        RestClient.getReqResService().getRecipes(10)
            .enqueue(object : Callback<RecipeResponse>{
                override fun onResponse(p0: Call<RecipeResponse>, response: Response<RecipeResponse>) {
                    if (response.isSuccessful) {
                        val recipes = response.body()?.recipes?.toMutableList() ?: mutableListOf()
                        adapter = RecipeCardAdapter(recipes, mainActivity)
                        binding.recyclerView.apply {
                            layoutManager = GridLayoutManager(applicationContext, 1)
                            this.adapter = this@MainActivity.adapter
                            val swipeHandler = SwipeToDeleteCallback(this@MainActivity.adapter)
                            val itemTouchHelper = ItemTouchHelper(swipeHandler)
                            itemTouchHelper.attachToRecyclerView(this)
                        }
                    }
                }

                override fun onFailure(p0: Call<RecipeResponse>, p1: Throwable) {
                    p1.printStackTrace()
                }

            })
    }

    override fun onClick(recipe: Recipe) {
        val intent = Intent(applicationContext, DetailActivity::class.java)
        intent.putExtra(RECIPE_ID_EXTRA, recipe)
        startActivity(intent)
    }
}