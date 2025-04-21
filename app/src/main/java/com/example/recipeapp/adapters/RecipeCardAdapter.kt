package com.example.recipeapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.api.Recipe
import com.example.recipeapp.databinding.RecipeCellBinding
import com.example.recipeapp.helpers.RecipeClickListener
import com.example.recipeapp.viewHolders.RecipeViewHolder

class RecipeCardAdapter(private var recipes: MutableList<Recipe>, private val recipeClickListener: RecipeClickListener) : RecyclerView.Adapter<RecipeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val from  = LayoutInflater.from(parent.context)
        val binding = RecipeCellBinding.inflate(from, parent, false)
        return RecipeViewHolder(binding, recipeClickListener)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bindRecipe(recipes[position])
    }

    fun deleteItem(position: Int) {
        recipes.removeAt(position)
        notifyItemRemoved(position)
    }
}