package com.example.mob_ayoub_project.models

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mob_ayoub_project.data.Cuisine
import com.example.mob_ayoub_project.data.Recipe
import com.example.mob_ayoub_project.data.Utils
import com.example.mob_ayoub_project.network.Recipe.RecipeService
import kotlinx.coroutines.launch

class CuisineViewModel  : ViewModel() {

    private var cuisineChoosed = mutableStateOf<Cuisine?>(null)
    var allRecipesFromCuisine: MutableState<List<Recipe>> = mutableStateOf(emptyList())

    fun setCuisine(newCuisine: Cuisine) {
        cuisineChoosed.value = newCuisine
    }

    fun fetchRecipesFromCuisine() {
        viewModelScope.launch {
            try {
                val cuisine = cuisineChoosed.value
                if (cuisine != null) {
                    RecipeService.initializeRecipeClient()
                    val responseRecipes =
                        RecipeService.recipeClient?.chooseCuisine(cuisine.name, Utils.apiKeyRecipe)
                    if (responseRecipes != null) {
                        allRecipesFromCuisine.value = responseRecipes.results
                        Log.i("Recipe from Cuisine", allRecipesFromCuisine.toString())
                    } else {
                        Log.e("Recipe from Cuisine", "Reponse de la recectte est null")
                    }
                } else {
                    Log.e("Recipe from Cuisine", "Aucune cuisine sélectionnée")
                }
            } catch (e: Exception) {
                Log.e("Recipe from Cuisine", "La reception des recette n'a pas fonctionné")
                Log.e("Recipe from Cuisine", e.message, e)
                e.printStackTrace()
            }
        }
    }
}