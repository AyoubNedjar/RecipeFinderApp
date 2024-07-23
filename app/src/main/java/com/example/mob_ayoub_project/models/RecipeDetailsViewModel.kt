package com.example.mob_ayoub_project.models

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mob_ayoub_project.data.InfosFromOneRecipe
import com.example.mob_ayoub_project.data.Recipe
import com.example.mob_ayoub_project.data.Utils
import com.example.mob_ayoub_project.network.Recipe.RecipeService
import kotlinx.coroutines.launch

class RecipeDetailsViewModel : ViewModel() {

    var recipeChoosed = mutableStateOf<Recipe?>(null)
    var resultsInfosFromOneRecipe = mutableStateOf<InfosFromOneRecipe?>(null)


    fun setRecipeChoosed(newRecipe: Recipe) {
        recipeChoosed.value = newRecipe
    }

    fun fetchInfosFromRecipe() {
        val idRecipeChoosed = recipeChoosed.value?.id
        if (idRecipeChoosed != null) {
            viewModelScope.launch {
                try {
                    val responseForTheRecipe = RecipeService.recipeClient?.infosForOneRecipe(idRecipeChoosed, Utils.apiKeyRecipe)
                    responseForTheRecipe?.let { response ->
                        resultsInfosFromOneRecipe.value = response
                        Log.i("INFOS SUR RECETTE", resultsInfosFromOneRecipe.toString())
                    }
                } catch (e: Exception) {
                    Log.e("recherche recette infos", "erreur produite : ${e.message}", e)
                }
            }
        } else {
            Log.e("recherche recette infos", "Recette ID est nulle")
        }
    }
}