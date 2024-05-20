package com.example.mob_ayoub_project.data


data class InfosFromOneRecipe(
    val image: String? = null,
    val title: String? = null,
    val veryHealthy: Boolean? = null,
    val summary:String? = null,
    val instructions: String? = null,
    val extendedIngredients: List<Ingredients> = listOf()
)
