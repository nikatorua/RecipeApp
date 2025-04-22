package com.example.recipeapp.viewHolders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.recipeapp.api.Recipe
import com.example.recipeapp.databinding.RecipeCellBinding
import com.example.recipeapp.helpers.RecipeClickListener

class RecipeViewHolder(private val cardCellBinding: RecipeCellBinding, private val recipeClickListener: RecipeClickListener) : RecyclerView.ViewHolder(cardCellBinding.root) {
    fun bindRecipe(recipe: Recipe){
        cardCellBinding.recipeTitle.text = recipe.name

        Glide.with(cardCellBinding.Cover.context)
            .load(recipe.image)
            .apply(RequestOptions.centerCropTransform())
            .into(cardCellBinding.Cover)

        cardCellBinding.recipeCard.setOnClickListener {
            recipeClickListener.onClick(recipe)
        }
    }
}