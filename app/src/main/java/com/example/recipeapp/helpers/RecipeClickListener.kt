package com.example.recipeapp.helpers

import com.example.recipeapp.api.Recipe

interface RecipeClickListener {
    fun onClick(recipe: Recipe)
}