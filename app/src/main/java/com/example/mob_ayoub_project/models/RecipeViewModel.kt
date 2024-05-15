package com.example.mob_ayoub_project.models

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mob_ayoub_project.data.Cuisine
import com.example.mob_ayoub_project.data.InfosFromOneRecipe
import com.example.mob_ayoub_project.data.Recipe
import com.example.mob_ayoub_project.network.Recipe.RecipeService
import com.example.mob_ayoub_project.network.Recipe.RecipesResponse
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel (){

    val apiKey = "e9e5c00dabbd4da198c72832aa7c0619"

    private var cuisineChoosed = mutableStateOf<Cuisine?>(null)


    var results: MutableState<List<Recipe>> = mutableStateOf(emptyList())

    var recipeChoosed  = mutableStateOf<Recipe?>(null)

    var resultsInfosFromOneRecipe = mutableStateOf<InfosFromOneRecipe?>(null)




    fun setRecipeChoosed(newRecipe : Recipe){
        recipeChoosed.value = newRecipe
    }
    fun setCuisine(newCuisine : Cuisine) {
        cuisineChoosed.value = newCuisine;

    }
    fun fetchRecipesFromCuisine(){
        viewModelScope.launch {
            try {
                val cuisine = cuisineChoosed.value
                if(cuisine!=null){
                    RecipeService.initializeRecipeClient()
                    val responseRecipes = RecipeService.recipeClient?.chooseCuisine(cuisine.name,apiKey)
                    if (responseRecipes != null) {
                        results.value= responseRecipes.results
                        Log.i("Recipe from Cuisine", results.toString())
                    }else {
                        Log.e("Recipe from Cuisine", "Response recipes is null")
                    }
                }else{
                    Log.e("Recipe from Cuisine", "Aucune cuisine sélectionnée")
                }
            }catch(e: Exception){
                Log.e("Recipe from Cuisine", "La reception des recette n'a pas fonctionné")
                Log.e("Recipe from Cuisine", e.message, e)
                e.printStackTrace()
            }
        }
    }


    fun fetchInfosFromRecipe(){

        val idRecipeChoosed = recipeChoosed.value?.id
        if (idRecipeChoosed != null) {
            viewModelScope.launch {
                try {
                    val responseForTheRecipe = RecipeService.recipeClient?.infosForOneRecipe(idRecipeChoosed, apiKey)

                    responseForTheRecipe?.let { response ->
                        resultsInfosFromOneRecipe.value = response
                        Log.i("INFOS SUR RECETTE", resultsInfosFromOneRecipe.toString())
                    }

                } catch (e: Exception) {
                    // Gérer l'erreur ici
                    Log.e("Fetch Recipe Infos", "An error occurred: ${e.message}", e)
                }
            }
        } else {
            Log.e("Fetch Recipe Infos", "Recipe ID is null")
        }
    }
}