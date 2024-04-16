package com.example.mob_ayoub_project.models

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mob_ayoub_project.data.Cuisine
import com.example.mob_ayoub_project.data.Recipe
import com.example.mob_ayoub_project.network.Recipe.RecipeService
import com.example.mob_ayoub_project.network.Recipe.RecipesResponse
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel (){

    private var cuisineChoosed = mutableStateOf<Cuisine?>(null)

    var results  : List<Recipe> = listOf()



    fun setCuisine(newCuisine : Cuisine) {
        cuisineChoosed.value = newCuisine;

    }
    fun fetchRecipesFromCuisine(){
        viewModelScope.launch {

            try {
                val cuisine = cuisineChoosed.value
                if(cuisine!=null){
                    RecipeService.initializeRecipeClient()
                    val responseRecipes = RecipeService.recipeClient?.chooseCuisine(cuisine.name)
                    if (responseRecipes != null) {
                        results = responseRecipes.results
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

                e.printStackTrace();
            }
        }
    }
}