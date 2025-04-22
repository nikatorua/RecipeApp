package com.example.recipeapp.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ReqResService {
    @GET("recipes")
    fun getRecipes(@Query("limit") limit: Int): Call<RecipeResponse>
}