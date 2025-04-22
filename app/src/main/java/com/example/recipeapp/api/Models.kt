package com.example.recipeapp.api

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

var RECIPE_ID_EXTRA = "RecipeExtra"

@Parcelize
data class Recipe (
    var id: Long?,
    val name: String?,
    var ingredients: List<String>?,
    var instructions: List<String>?,
    var caloriesPerServing: Int?,
    var image: String?,
    var rating: Float?,
    var reviewCount: Int?,
    var isExpanded:Boolean = false
): Parcelable

data class RecipeResponse(
    val recipes: List<Recipe>
)