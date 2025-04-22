package com.example.recipeapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.recipeapp.api.RECIPE_ID_EXTRA
import com.example.recipeapp.api.Recipe
import com.example.recipeapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var recipe: Recipe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recipe = intent.getParcelableExtra(RECIPE_ID_EXTRA) ?: return

        binding.recipeTitle.text = recipe.name
        binding.recipeIngredients.text = recipe.ingredients?.joinToString(separator = " , ")
        binding.recipeInstructions.text = recipe.instructions?.joinToString(separator = " , ")
        binding.caloriesText.text = "${recipe.caloriesPerServing} calories per serving"
        binding.ratingText.text = "${recipe.rating}/5 (${recipe.reviewCount} ratings)"
        Glide.with(binding.Cover.context)
            .load(recipe.image)
            .apply(RequestOptions.centerCropTransform())
            .into(binding.Cover)
    }
}