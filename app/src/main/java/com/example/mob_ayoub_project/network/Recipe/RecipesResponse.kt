package com.example.mob_ayoub_project.network.Recipe

import com.example.mob_ayoub_project.data.Recipe
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class RecipesResponse(
    // @SerialName("results")
    //lateinit var recipes: List<Recipe>

    val results: List<Recipe>
)
